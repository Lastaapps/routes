# Routes navigation with parcelable args for Android

Hi, in not only the latest versions of navigation for **Fragments** and **Compose** (`2.4.0-alpha04`) there's no way to pass custom arguments like a `Parcelable` or a `Serializable` to a destination without messing manually with the `currentBackStackEntry` (sometimes unreliable and not general enough) or using serialized objects as route parameters (messy and problematic, not only because of `'\'` character).

### Future

In [this talk](https://youtu.be/4srssoBo0HU?t=2024) they said that they are not going to implement it themselves and how should these situations be [correctly handled](https://youtu.be/4srssoBo0HU?t=2249). So use my code only for the migration purposes, it may stop working anytime without warning. Rather migrate towards the `ViewModel` approach.

But to resolve the arguments issue temporally I have created this example repository how navigation with arguments can be achieved using reflections in, I think and hope, safe way, both for Fragments and Compose.

### Disclaimer

This may not be production/release ready, additional testing is required. Also this solution got discovered by me, a developer with not as long experience and deep navigation library understanding, so please don't be rude if I did something totally stupid, just tell me. Also don't expect deeper explanation and long term support. This approach works in my app just fine, so it should be safe. Should...

### Implementation

- adding [Kotlin reflection](https://kotlinlang.org/docs/reflection.html) into your Gradle module
  dependencies
- adding [proguard rules](compose/proguard-rules.pro)
- copying [NavigationExtencions.kt](compose/src/main/kotlin/cz/lastaapps/routes/compose/navigating/NavigationExtencions.kt) and [Reflections.kt](compose/src/main/kotlin/cz/lastaapps/routes/compose/navigating/Reflections.kt) file, updating the package name
- importing and calling the `navigate(String, Bundle, NavOptionsBuilder.() -> Unit)` method

### Good to know

Some method calls including the first one take a while, but the other ones are just fine. Reflections are just not a great solution... So, migrate to the `ViewModel` system as soon as possible.

Have a nice day

Thanks for reading!

