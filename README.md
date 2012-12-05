## IconicTextView

IconicTextView is an extension of Android TextView class which provides support for some iconic fonts.

Try out the sample application.
![IconicTextView example](http://habrastorage.org/storage2/b8f/243/d96/b8f243d96a656f3b94e2ee6e5d36f8f9.png?raw=true)
 
## Usage

### Including in your project
	
IconicTextView is presented as an [Android library project](http://developer.android.com/guide/developing/projects/projects-eclipse.html). 
It is not a standalone JAR because fonts are stored as a raw resources.

You can include this project by [referencing it as a library project](http://developer.android.com/guide/developing/projects/projects-eclipse.html#ReferencingLibraryProject) in Eclipse or ant.

### Sample usage
**Layout:**
```xml
<com.atermenji.android.iconictextview.IconicTextView
	android:id="@+id/iconic_text_view"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	android:gravity="center"
	android:textSize="100sp" />
```
**Code:**
```java
IconicTextView iconicTextView = (IconicTextView) findViewById(R.id.iconic_text_view);
iconicTextView.setIcon(EntypoSocialIcon.GITHUB);
iconicTextView.setTextColor(Color.GREEN);
```

## Available fonts
	
 - Entypo pictograms by Daniel Bruce — www.entypo.com

## License

* [Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

**Note that all fonts have their own license.**

