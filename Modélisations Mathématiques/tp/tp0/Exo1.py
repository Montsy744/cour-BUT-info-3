from math import exp

print(123+456)
print(123*456)
print(123**456)
print(123/45)
print(123//45)
print(123%45)
#print(42/0)

exp(1.0)

x=1
print(type(x))
x="toto"
print(type(x))

print(3*x)

#print(40 + "2")
print(40 + int("2"))
print(str(40) + "2")

def f(x):
    return x**4 + 10 * x**3 + 100 * x + 1789

print(f(42))

# 1. Définir une variable contenant une chaîne d'au moins 8 caractères
chaine = "pythonique"

# 2. Obtenir la longueur de la chaîne
longueur = len(chaine)
print("Longueur :", longueur)

# 3. Afficher le premier caractère (indice 0)
print("Premier caractère :", chaine[0])

# 4. Afficher le dernier caractère (indice négatif)
print("Dernier caractère :", chaine[-1])

# 5. Afficher les caractères entre le second et le cinquième (bornes incluses)
# En Python, les indices vont de 0 à n-1.
# Second caractère = indice 1, Cinquième caractère = indice 4.
# Avec le slicing [début:fin], la borne de fin est exclue, donc on utilise [1:5].
print("Du 2e au 5e caractère inclus :", chaine[1:5])

# 6. Tester l'appartenance du caractère 'z' à la chaîne
print("Est-ce que 'z' est dans la chaîne ?", "z" in chaine)

# 7. Remplacer le second caractère par un 'z'
# Les chaînes sont IMMUTABLES en Python : on ne peut pas faire `chaine[1] = 'z'`.
# Il faut construire une nouvelle chaîne en combinant le début, le 'z' et la suite :
chaine_modifiee = chaine[0] + "z" + chaine[2:]
print("Chaîne modifiée :", chaine_modifiee)