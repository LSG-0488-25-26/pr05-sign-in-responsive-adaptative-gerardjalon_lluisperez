# Institut App - Responsive & Adaptive Login

Esta aplicación Android ha sido desarrollada utilizando Kotlin y Jetpack Compose. Implementa un flujo de autenticación (Inicio de sesión y Registro) para un "Institut Tecnològic" ficticio, siguiendo el patrón de arquitectura MVVM (Model-View-ViewModel).

El objetivo principal del proyecto es demostrar la implementación de diseños **Adaptativos** (Adaptive) y **Responsivos** (Responsive) utilizando Material Design 3.

## Funcionalidades Principales

### 1. Diseño Adaptativo (Adaptive)
La aplicación detecta el tamaño de la ventana del dispositivo (`WindowWidthSizeClass`) y adapta la interfaz de usuario a tres escenarios:
* **Compact (Móviles en vertical):** Diseño lineal vertical ocupando todo el ancho.
* **Medium (Tablets verticales / Plegables):** Diseño centrado con márgenes laterales para mejorar la legibilidad.
* **Expanded (Tablets horizontales / Escritorio):** Diseño de pantalla dividida (Split Screen). Muestra un panel informativo a la izquierda y el formulario a la derecha.

### 2. Diseño Responsivo (Responsive)
La interfaz reacciona a los cambios de orientación del dispositivo.
* En dispositivos móviles, al girar a modo horizontal (Landscape), se ocultan elementos no esenciales (como el banner superior) para priorizar el espacio del formulario y el teclado.

### 3. Validación de Datos
Los formularios incluyen validación lógica en tiempo real. El botón de registro permanece deshabilitado hasta que se cumplen los requisitos:
* Campos obligatorios no vacíos.
* Formato de correo electrónico válido.
* Teléfono numérico con longitud mínima.
* Coincidencia entre contraseña y confirmación de contraseña.
* Aceptación de términos y condiciones.

### 4. Arquitectura MVVM
* **Model:** `SignUpUiState` (Define el estado de la interfaz).
* **View:** `LoginView`, `SignUpView` (Componentes UI declarativos).
* **ViewModel:** `SignUpViewModel` (Gestiona la lógica de negocio, el estado y las validaciones).

## Estructura del Proyecto

El código fuente se encuentra bajo el paquete `com.example.loginresponsive`:

* **MainActivity.kt:** Punto de entrada. Calcula el `WindowSizeClass` y configura la navegación.
* **EntryPoint.kt:** Configuración del `NavHost` y rutas de navegación.
* **Routes.kt:** Definición de las rutas de navegación (Login, SignUp).
* **Model/**
    * `SignUpUiState.kt`: Data class que representa el estado del formulario.
* **View/**
    * `LoginView.kt`: Pantalla de inicio de sesión.
    * `SignUpView.kt`: Pantalla de registro con lógica adaptativa.
* **ViewModel/**
    * `SignUpViewModel.kt`: Gestión de eventos de usuario y actualización de estado.

## Requisitos Técnicos

* Android Studio (Versión recomendada: Iguana o superior).
* Kotlin.
* Jetpack Compose con Material3.
* Dependencia externa clave: `androidx.compose.material3:material3-window-size-class`.

## Instalación y Ejecución

1.  Clonar o descargar el proyecto.
2.  Abrir el proyecto en Android Studio.
3.  Esperar a que Gradle sincronice las dependencias.
4.  Ejecutar la aplicación en un emulador o dispositivo físico.
5.  Para probar el diseño adaptativo, se recomienda usar un emulador de Tablet o "Resizable" en Android Studio.

## Capturas
Login default:
<img width="392" height="880" alt="image" src="https://github.com/user-attachments/assets/61785769-f016-4dd2-8d78-b73717fd8fe3" />
<hr>
Login landscape:
<img width="954" height="891" alt="image" src="https://github.com/user-attachments/assets/0ae92348-a0a8-4647-bd08-9b4b66d5078c" />
<hr>

Register default:
<img width="424" height="885" alt="image" src="https://github.com/user-attachments/assets/9081d678-900c-4deb-9cee-2d3e3530c341" />
<hr>

Register landscape:
<img width="1919" height="989" alt="image" src="https://github.com/user-attachments/assets/e1cb639a-1da4-436c-b269-aea0bf63df87" />




