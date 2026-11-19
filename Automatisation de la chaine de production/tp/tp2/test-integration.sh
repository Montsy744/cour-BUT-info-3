#!/bin/sh
set -e

API_URL="${API_URL:-http://localhost:8080}"
REPORT_FILE="integration-report.xml"

write_junit() {
  failures=$1
  err_msg=$2
  cat <<EOF > "$REPORT_FILE"
<?xml version="1.0" encoding="UTF-8"?>
<testsuite name="IntegrationTests" tests="2" failures="$failures">
  <testcase classname="IntegrationTests" name="POST_user"/>
  <testcase classname="IntegrationTests" name="GET_users">
    $([ "$failures" -gt 0 ] && echo "<failure message=\"$err_msg\"/>")
  </testcase>
</testsuite>
EOF
}

for i in $(seq 1 30); do
  if curl -s -f "$API_URL/users" > /dev/null 2>&1; then
    break
  fi
  sleep 1
done

POST_RES=$(curl -s -w "\n%{http_code}" -X POST "$API_URL/users" \
  -H "Content-Type: application/json" \
  -d '{"nom":"Doe","prenom":"John","telephone":"0600000000","adresse":"123 Rue De la Gare","codePostal":"75001","ville":"Paris","mdp":"mdp","email":"user@domain.tld"}')

POST_CODE=$(echo "$POST_RES" | tail -n1)
if [ "$POST_CODE" -lt 200 ] || [ "$POST_CODE" -ge 300 ]; then
  echo "Échec POST : code HTTP $POST_CODE"
  write_junit 1 "POST /users a renvoyé HTTP $POST_CODE"
  exit 1
fi

GET_RES=$(curl -s -f "$API_URL/users") || {
  echo "Échec GET /users"
  write_junit 1 "GET /users inaccessible"
  exit 1
}

if ! echo "$GET_RES" | grep -q "Doe"; then
  echo "Échec vérification JSON : 'Doe' introuvable dans $GET_RES"
  write_junit 1 "Utilisateur Doe absent de la réponse GET"
  exit 1
fi

echo "Tests d'intégration réussis !"
write_junit 0 ""
exit 0