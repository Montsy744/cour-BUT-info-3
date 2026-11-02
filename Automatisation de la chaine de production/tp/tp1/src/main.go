package main

import (
	"fmt"
	"net/http"
)

func main() {
	http.HandleFunc("/", helloWorld)
	http.HandleFunc("/r5.04", r504)

	fmt.Println("API ready !")
	fmt.Println("Listening at : http://localhost:8080")
	_ = http.ListenAndServe(":8080", nil)
}

func helloWorld(writer http.ResponseWriter, request *http.Request) {
	_, _ = fmt.Fprint(writer, "Hello World!\n")
}

func r504(writer http.ResponseWriter, request *http.Request) {
	_, _ = fmt.Fprint(writer, "Automatisation de la chaine de production")
}
