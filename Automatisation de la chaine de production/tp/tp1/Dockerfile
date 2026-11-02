FROM golang:1.22-alpine

WORKDIR /app

# Téléchargement des dépendances
COPY go.mod ./
RUN go mod download

# Copie du code source et compilation
COPY . .
RUN go build -v -o /hello-go .

# Exposition du port de l'application
EXPOSE 8080

# Commande de lancement
CMD ["/hello-go"]