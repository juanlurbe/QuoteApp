# 📚 QuoteApp

Aplicación Android desarrollada con **Jetpack Compose**, que permite al usuario obtener frases aleatorias desde una API externa con Retrofit y guardarlas como favoritas utilizando Room. 

---

## ✨ Funcionalidades

- 🔄 Obtener una frase aleatoria desde la API de [API Ninjas - Quotes](https://api-ninjas.com/api/quotes)
- 💾 Guardar frases como favoritas en base de datos local (Room)
- 📃 Ver listado de frases favoritas y eliminarlas

---

## 🧭 Estructura del proyecto

com.tp3.grupo4/
├── data/
│   ├── local/        # Room: DAO y entidades
│   ├── remote/       # Retrofit: API + data source
│   └── repository/   # Implementación del repositorio
├── domain/
│   ├── model/        # Modelos del dominio
│   ├── repository/   # Interfaces
│   └── usecase/      # Casos de uso
├── presentation/
│   ├── screen/       # Pantallas de UI (Compose)
│   ├── navigation/   # NavHost y rutas
│   └── viewmodel/    # ViewModels
├── di/               # Módulos de Hilt
├── core/             # Config y constantes
└── QuoteApplication.kt  # Clase Application


