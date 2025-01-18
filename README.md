# Orange Assessment BooksApp

For the apk build, check at the Releases section on the right side of the repository.

# Used Technologies
- RecyclerView
- Jetpack Navigation Graph
- Dagger Hilt
- Kotlin Reflection
- Kotlin Flow
- LiveData
- ViewBinding + MVVM + Clean Architecture
- Glide
- Auto-constructor injection with Annotation Processing

# Setup
- Used the latest Android Studio Koala version and Android SDK version 35.
- Clone this project and make sure that android.enableJetifier=true for allowing the app to inject.
- For isolate use on any project, use the exact dependencies at the Gradle app module level also,
the plugin for enabling kapt across the project module. (Make sure that the versions are constants at gradle/libs.versions.toml
- Rebuild the project through Build >> Rebuild Project and the Hilt injection locators will be generated automatically.
-  Sync the project through the project module.

# Drawbacks
- Problem :Using Google Books API without apiKey exceeded the limit quota for get the data from Google Cloud API.
- Solution: Used personal apiKey from my personal account.
- Future solution for security wise: Saving personal Keys through gradle.properties and use BuildConfig class.

# Screenshots

- Navigation graph for the app

![Screenshot (121)](https://github.com/user-attachments/assets/afe0a96e-5aad-4b51-b8c2-b3f30d5ea3a3)

- Screenshots from the emulator.
![Screenshot_2025 01 18_05 25 40 526](https://github.com/user-attachments/assets/9aae210a-926b-4449-8d07-ee2d693fbd92)
![Screenshot_2025 01 18_05 23 20 410](https://github.com/user-attachments/assets/114fcac0-714b-4cd9-8d72-6d926b15cf21)
![Screenshot_2025 01 18_05 23 28 643](https://github.com/user-attachments/assets/b38b4d8a-6bac-4ef2-83da-fe525723e6ea)
![Screenshot_2025 01 18_05 23 28 643](https://github.com/user-attachments/assets/88c569c5-8e7a-4323-834d-975b5ce49808)
