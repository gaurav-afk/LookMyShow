# 🎬 LookMyShow (IN-PROGRESS)

## Overview
LookMyShow is an Android app designed to help users discover the latest movies, shows, and events nearby. The app provides showtimes, and booking integration — all in a modern, smooth, and intuitive interface.  
Currently, the app uses static data for movies, showtimes, and theatres.
Users can browse and book movies, and see theatre locations on interactive maps. The app is built using **MVVM + Clean Architecture**, **Jetpack Compose**, and modern Android development practices.

## Screenshots
<img width="200" height="400" alt="Screenshot_20251020_173701" src="https://github.com/user-attachments/assets/c03fb430-6efc-4e77-a3b1-3a1216f56769" />
<img width="200" height="400" alt="Screenshot_20251020_173623" src="https://github.com/user-attachments/assets/2c204504-ba9b-436b-9cda-e5fe2b8d7596" />
<img width="200" height="400" alt="Screenshot_20251013_170936" src="https://github.com/user-attachments/assets/f61061b7-e6a9-445a-8ce4-a73f18380fac" />
<img width="200" height="400" alt="Screenshot_20251013_170945" src="https://github.com/user-attachments/assets/66dca366-a564-43be-8838-ae693dcf2ff3" />
<img width="200" height="400" alt="Screenshot_20251020_173502" src="https://github.com/user-attachments/assets/ee30cae7-2fde-44da-8279-9e9500bb66e8" />
<img width="200" height="400" alt="Screenshot_20251020_173511" src="https://github.com/user-attachments/assets/c5a6a94b-980c-4018-a1e4-dc0f0a098894" />
<img width="200" height="400" alt="Screenshot_20251020_173519" src="https://github.com/user-attachments/assets/a317f398-d10c-41f3-8e08-ece56461530e" />
<img width="200" height="400" alt="Screenshot_20251021_110843" src="https://github.com/user-attachments/assets/78d55820-a341-4a9d-917a-6f18e9d7b230" />
<img width="200" height="400" alt="Screenshot_20251021_172952" src="https://github.com/user-attachments/assets/00d15587-1015-4acf-93aa-d6ab0c04c3f4" />
<img width="200" height="400" alt="Screenshot_20251021_173026" src="https://github.com/user-attachments/assets/17f59c5d-ef25-4846-8347-e2dd7525dfc0" />
<img width="200" height="400" alt="Screenshot_20251021_173018" src="https://github.com/user-attachments/assets/58d40181-1177-4f2a-8d08-78efc6e628ae" />
<img width="200" height="400" alt="Screenshot_20251020_173617" src="https://github.com/user-attachments/assets/fa830724-7328-41e5-a7e9-6ac69659aa3c" />


## Features
- **Firebase Authentication**: Secure login/signup with email/password and session management.  
- **Movie Listings**: Browse movies running in nearby theatres.  
- **Interactive Maps**: Integrated with **OSM + MapTiler** to display theatre locations.  
- **Clean Architecture**: Built using **MVVM + Clean Architecture** principles for scalable and maintainable code.  
- **Modern Tech Stack**:  
  - **Kotlin & Jetpack Compose** for UI  
  - **Coroutines & Flow** for async and reactive data handling  
  - **Hilt** for dependency injection  
  - **ViewModel** for lifecycle-aware state management  

## Requirements
- Android SDK version 30 or higher.  
- Internet connection for signup and login.

## Libraries & Tools
- **Jetpack Compose** for UI  
- **Firebase Authentication** for login/signup  
- **OSM + MapTiler** for maps  
- **Coil** for image loading  
- **Coroutines** for asynchronous tasks
- **Hilt** for dependency injection  
- **ViewModel** for lifecycle-aware state management  
- **RoomDB** to store bookedTickets
- **Flow** for reactive data handling
  
## Architecture:
- **MVVM + Clean** Architecture

## Compose APIs:
- **LaunchedEffect and DisposableEffect** for lifecycle-aware state handling

## Getting Started

### Clone the repository
```bash
git clone https://github.com/gaurav-afk/LookMyShow.git
cd LookMyShow

