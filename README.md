# Mini Training Modules App (Android)

## Overview
This is a native Android application built using **Kotlin** that displays a list of training modules and allows users to mark them as **Completed** or **Pending**.  
The app uses **local persistence** and follows a **clean MVVM architecture** with Jetpack components.

---

## Tech Stack
- **Language:** Kotlin  
- **Architecture:** MVVM (Model–View–ViewModel)  
- **UI:** XML, RecyclerView, ConstraintLayout  
- **Persistence:** Room Database  
- **Jetpack Components:** ViewModel, LiveData  

---

## How to Run the Project

1. Clone the repository (master branch):
   ```bash
   git clone -b master <repository-url>
2. setup project, connect to emulator/device and run the applcation from android studio.

---

## Why I Chose This Approach

1. MVVM provides a clean and scalable architecture.
2. Room ensures data persistence without a backend.
3. ViewModel + LiveData make the UI lifecycle-aware and stable.
4. Simple XML-based UI keeps the solution easy to understand and extend.

---

## Imporvement if i got one more day

1. Better User Friendly and responsive UI design.
2. Fragment based Application and usage of navigation componet: nav graph.
3. Usage of Hilt Dependency injection to reduce boilerplate code, to enhance codebase and memory management.
4. Search functionality with debounced input for quickly finding training modules.
5. StateFlow + Coroutines for more modern and reactive state management.
6. Better list performance using DiffUtil and ListAdapter.
7. Error and empty-state handling for better UX when no data is available.



