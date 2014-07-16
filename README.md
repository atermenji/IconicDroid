## IconicDroid [![Build Status](https://travis-ci.org/atermenji/IconicDroid.png)](https://travis-ci.org/atermenji/IconicDroid)

IconicDroid is a custom [Android Drawable](http://developer.android.com/reference/android/graphics/drawable/Drawable.html) which allows to draw icons from several iconic fonts.

![IconicDroid example](http://habrastorage.org/storage2/b8f/243/d96/b8f243d96a656f3b94e2ee6e5d36f8f9.png?raw=true)
 
## Usage

### Including in your project
	
IconicDroid is presented as an [Android library project](http://developer.android.com/guide/developing/projects/projects-eclipse.html). 
It is not a standalone JAR because fonts are stored as a raw resources.

You can include this project by [referencing it as a library project](http://developer.android.com/guide/developing/projects/projects-eclipse.html#ReferencingLibraryProject) in Eclipse or ant.

### Sample usage
**Code:**
```java
IconicFontDrawable iconicFontDrawable = new IconicFontDrawable(getContext());
iconicFontDrawable.setIcon(EntypoSocialIcon.GITHUB);
iconicFontDrawable.setIconColor(Color.GREEN);

findViewById(R.id.some_view).setBackground(iconicFontDrawable);
```

## Available fonts
	
 - Entypo pictograms by Daniel Bruce — www.entypo.com
 - FontAwesome by Dave Gandy - http://fortawesome.github.com/Font-Awesome/
 - Iconic font by P.J. Onori - http://somerandomdude.com/work/iconic/
 - Glyph Icon Font from WebHostingHub - http://www.webhostinghub.com/glyphs/

## License

* [Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

**Note that all fonts have their own license.**



[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/atermenji/iconicdroid/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

