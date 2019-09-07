# Andy

<b>This library is basically a collection of helper methods, which we use while building android apps.</b>

Both <b>Java</b> and <b>Kotlin</b> version are persent in it. All the methods and parameters are same in both of them.

For Java Use below dependency:-
```
implementation 'com.menasr.andy:andy:1.0.1'
```

For Kotlin use below dependency:-
```
implementation 'com.menasr.andyktx:andyktx:1.0.1'
```

or you can direct download the library and use it in you project.

#### Prerequisites
Initialize once in your app(for ex - Splash screen)
```
Andy.init(context);
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

AndyResources res = new AndyResources(this.getApplicationContext())

or in kotlin

val res = AndyResources(this.applicationContext)
```

#### There are some other class, which object are not created by default,
1. PermissionManager - Responsible for handling all permissions upto android Pie(28). Just create an object for use. The class contains all the necessay documents for help. Please, refer to class docs for usage(For both java and Kotlin).


Rest is self explanatory, or your can just use <b>ctrl+space</b> to see method defination, In every method there is method declaration and details are present.

### Upcoming releases
 1. New methods
 2. Screenshots for better understanding
