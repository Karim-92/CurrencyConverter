<p align="center">  
Currency Converter is a demo application Utilizing the MVVM + Kotlin + Navigation Components + Coroutines + Livedata + Room + Hilt.<br>

It allows the user to get a list of currency exchange rates using the Fixer.io API, or work in offline mode. You can then use the rates to exchange for Euros.</br>

## Application MAD Score cards

![Summary](https://github.com/Karim-92/CurrencyConverter/blob/master/mad_scorecard/summary.png)
![Jetpack](https://github.com/Karim-92/CurrencyConverter/blob/master/mad_scorecard/jetpack.png)

## Architecture
  - Currency Converter is based on MVVM architecture and a repository pattern.

![architecture](https://user-images.githubusercontent.com/24237865/77502018-f7d36000-6e9c-11ea-92b0-1097240c8689.png)
  
## Usage
  - To use the application and build it correctly you need to signup for the fixer.io developer API program, get a developer Key and create a file called "keys.properties" in the root directory of the project. You'll then need to add an entry with your developer key. 
  
  ```xml
  API_KEY = "Your developer key here"
```
  And that's it! you're ready to build the project.
  
## Libraries used

  - [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
  - [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.
  - [Glide](https://github.com/bumptech/glide), [GlidePalette](https://github.com/florent37/GlidePalette) - loading images.
  - [Timber](https://github.com/JakeWharton/timber) - logging.
  - [Hilt](https://dagger.dev/hilt/) - Dependency injection library built on top of Dagger2.

## Influence
- This was built as a task for Maxab application process.

