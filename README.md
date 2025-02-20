# RxJava & RxAndroid with Retrofit - The Movie DB API

## 📌 Overview
This project is a **study repository** for exploring **RxJava** and **RxAndroid** in an Android application. It integrates **Retrofit** for networking and consumes **The Movie DB API** to fetch movie data asynchronously. The purpose of this project is to practice handling reactive streams, threading, and network calls efficiently.

## 🎯 Features
- Fetch a list of movies from **The Movie DB API** 🎬
- Display movie details including title, overview, and ratings ⭐
- Use **RxJava** to handle asynchronous network requests
- Apply **Schedulers** to manage background and UI thread execution
- Implement **Retrofit** with RxJava for API calls

## 🛠️ Tech Stack
- **Language:** Java
- **Architecture:** MVVM (Model-View-ViewModel)
- **Networking:** Retrofit
- **Reactive Programming:** RxJava & RxAndroid
- **UI Components:** XML

## 🔧 Setup
1. **Clone the repository:**
   ```sh
   git clone https://github.com/your-username/rxjava-rxandroid-movie-db.git
   ```
2. **Get API Key from The Movie DB**
   - Sign up at [The Movie DB](https://www.themoviedb.org/)
   - Generate an API key
3. **Create and add the API Key to `apiKey.properties`**:
   ```properties
   API_KEY=your_api_key_here
   ```
4. **Build and run the project in Android Studio** 🚀

## 📡 API Integration
- **Base URL:** `https://api.themoviedb.org/3/`
- **Endpoints Used:**
  - `/movie/popular` → Fetches popular movies

## 🛠️ Dependencies
```gradle
dependencies {
    implementation 'com.squareup.retrofit2:retrofit:2.4.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.4.0'
    implementation 'com.squareup.okhttp3:okhttp:3.11.0'
    implementation 'com.squareup.retrofit2:adapter-rxjava2:2.4.0'
    implementation 'io.reactivex.rxjava2:rxjava:2.1.0'
    implementation 'io.reactivex.rxjava2:rxandroid:2.1.0'
}
```

## 📌 Key Learnings
✅ How to integrate **RxJava** with **Retrofit**
✅ Using **Schedulers** for proper threading
✅ Managing **disposables** to avoid memory leaks
✅ Error handling with **RxJava operators**

## 🤝 Contributing
Feel free to fork this repository and contribute with new examples, fixes, or improvements!

## 📄 License
This project is licensed under the **MIT License**.

## Author
[Felippe Neves](https://github.com/felippeneves)
