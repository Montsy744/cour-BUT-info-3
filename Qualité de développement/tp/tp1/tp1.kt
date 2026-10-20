fun main() {
    println("=== TESTS DES EXERCICES DU TP ===")
    
    // Test Exercice 1.9 : Fonction squareInt
    println("Square de 5 : ${squareInt(5)}")
    
    // Test Exercice 2.1 : Recherche linéaire
    val maListe = listOf(10, 20, 30, 40, 50)
    println("Recherche linéaire de 30 : ${recherche_lineaire_iterative(30, maListe)}")
    
    // Test Exercice 2.2 : Recherche dichotomique
    val listeTriee = listOf(2, 5, 8, 12, 16, 23, 38)
    println("Recherche dicho (itérative) de 16 : ${recherche_dicho_iterative(16, listeTriee)}")
    
    // Test Exercice 2.3 : Tri par insertion
    val unsorted = listOf(12, 11, 13, 5, 6)
    println("Tri insertion non mutable : ${tri_insertion_non_mutable(unsorted)}")
}

// ==========================================
// 1. ÉCHAUFFEMENT ET FONCTIONS DE BASE
// ==========================================

fun squareInt(n: Int): Int = n * n

fun sumDoubles(a: Double, b: Double, c: Double): Double = a + b + c

fun duplicate(s: String): String = s + s

fun checkSign(n: Int): String = if (n > 0) "positive" else if (n < 0) "negative" else "zero"

fun abs(n: Int): Int = if (n >= 0) n else -n

fun findMax(a: Int, b: Int): Int = if (a > b) a else b

fun record(index: Int, status: String, message: String): String {
    return "$index [$status] $message\n"
}

fun show(i: Int, s: String, c: Char, d: Double) {
    println("i: $i")
    println("s: \"$s\"")
    println("c: '$c'")
    println("d: $d")
}

fun displayContent(s: String) {
    var i = 0
    while (i < s.length) {
        println(s[i])
        i++
    }
}

fun sum(n: Int): Int {
    var total = 0
    var i = 0
    while (i <= n) {
        total += i
        i++
    }
    return total
}

fun sumEven(n: Int): Int {
    var total = 0
    var i = 0
    while (i <= n) {
        if (i % 2 == 0) total += i
        i++
    }
    return total
}

fun factorial(n: Int): Long {
    var result = 1L
    for (i in 1..n) {
        result *= i
    }
    return result
}

fun sumOfFactorials(n: Int): Long {
    var total = 0L
    for (i in 1..n) {
        total += factorial(i)
    }
    return total
}

fun showHalfTriangle(n: Int) {
    for (i in 1..n) {
        println("#".repeat(i))
    }
}

fun alphabet(): String {
    var s = ""
    for (c in 'a'..'z') {
        s += c
    }
    return s
}

fun isLowerCase(c: Char): Boolean = c in 'a'..'z'

fun isValidIdentifier(s: String): Boolean {
    if (s.isEmpty()) return false
    val first = s[0]
    if (first !in 'a'..'z' && first !in 'A'..'Z' && first != '_') return false
    for (i in 1 until s.length) {
        val c = s[i]
        if (c !in 'a'..'z' && c !in 'A'..'Z' && c !in '0'..'9' && c != '_') return false
    }
    return true
}

// ==========================================
// 2. ALGORITHMIQUE EN KOTLIN
// ==========================================

// --- 2.1. Recherche linéaire ---

fun recherche_lineaire_iterative(e: Int, liste: List<Int>): Int {
    for (i in liste.indices) {
        if (liste[i] == e) return i
    }
    return -1
}

fun recherche_lineaire_rec(e: Int, liste: List<Int>, index: Int = 0): Int =
    when {
        index >= liste.size -> -1
        liste[index] == e -> index
        else -> recherche_lineaire_rec(e, liste, index + 1)
    }

// --- 2.2. Recherche dichotomique ---

fun recherche_dicho_iterative(e: Int, liste: List<Int>): Int {
    var bas = 0
    var haut = liste.size - 1
    while (bas <= haut) {
        val milieu = (bas + haut) / 2
        when {
            liste[milieu] == e -> return milieu
            liste[milieu] < e -> bas = milieu + 1
            else -> haut = milieu - 1
        }
    }
    return -1
}

fun recherche_dicho_rec(e: Int, liste: List<Int>, bas: Int, haut: Int): Int {
    if (bas > haut) return -1
    val milieu = (bas + haut) / 2
    return when {
        liste[milieu] == e -> milieu
        liste[milieu] < e -> recherche_dicho_rec(e, liste, milieu + 1, haut)
        else -> recherche_dicho_rec(e, liste, bas, milieu - 1)
    }
}

fun recherche_dicho_rec(e: Int, liste: List<Int>): Int {
    if (liste.isEmpty()) return -1
    val milieu = liste.size / 2
    val valeurMilieu = liste[milieu]
    return when {
        valeurMilieu == e -> milieu
        valeurMilieu < e -> {
            val res = recherche_dicho_rec(e, liste.subList(milieu + 1, liste.size))
            if (res == -1) -1 else milieu + 1 + res
        }
        else -> recherche_dicho_rec(e, liste.subList(0, milieu))
    }
}

fun recherche_dicho_null(e: Int, liste: List<Int>): Int? {
    val res = recherche_dicho_rec(e, liste)
    return if (res == -1) null else res
}

// --- 2.3. Tri par insertion ---

fun tri_insertion_mutable(liste: List<Int>): List<Int> {
    val mutableList = liste.toMutableList()
    for (i in 1 until mutableList.size) {
        val key = mutableList[i]
        var j = i - 1
        while (j >= 0 && mutableList[j] > key) {
            mutableList[j + 1] = mutableList[j]
            j--
        }
        mutableList[j + 1] = key
    }
    return mutableList
}

fun inserer(e: Int, liste: List<Int>): List<Int> {
    if (liste.isEmpty() || e <= liste[0]) {
        return listOf(e) + liste
    }
    return listOf(liste[0]) + inserer(e, liste.subList(1, liste.size))
}

fun tri_insertion_non_mutable(liste: List<Int>): List<Int> {
    if (liste.isEmpty()) return listOf()
    return inserer(liste[0], tri_insertion_non_mutable(liste.subList(1, liste.size)))
}