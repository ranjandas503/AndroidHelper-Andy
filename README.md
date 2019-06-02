# Andy

<b>This library is basically a collection of helper methods, which we use while building android apps.</b>

Both <b>Java</b> and <b>Kotlin</b> version are persent in it. All the methods and parameters are same in both of them.

For Java Use below dependency:-
```
</b>implementation 'com.menasr.andy:andy:1.0.1'</b>
```

For Kotlin use below dependency:-
```
<b>implementation 'com.menasr.andyktx:andyktx:1.0.1'</b>
```

or you can direct download the library and use it in you project.

#### Prerequisites
Initialize once in your app(for ex - Splash screen)
```
<b>Andy.init(context);</b>
```

After initialization you are ready to use it. You can use it in any <b>activity</b> or <b>fragment</b>, just by calling like..
```
Andy.IMAGE.<method name>;
Andy.INTENT.<method name>
Andy.MAPS.<method name>
Andy.FILES.<method name>
Andy.DEVICES.<method name>
.
.
.
and so on
```

Or you can directly initialize the class and use the <b>object</b> like
```
//Ex. For resources in Java

Res res = new Res(this.getApplicationContext())

or in kotlin

val res = Res(this.applicationContext)
```

Rest is self explanatory, or your can just use <b>ctrl+space</b> to see method defination, In every method there is method declaration and details are present.

### Upcoming releases
 1. New methods
 2. Screenshots for better understanding
