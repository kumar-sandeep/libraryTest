# libraryTest

It's an open source library
## Features
It has two Activities MainActivity and Main2Activity.
## 1) Main2Activity 
### Features in Main2Activity
Main2Activity retrieves the data stored in the firebase database and shows it on the screen.

## 2) MainActivity
### Features in MainActivity
a) MainActivity has a signup form which has email id,name,mobileno and dob field.
b) The fields email id, name, dob are mandatory.
c) The field mobileno is optional.
d) To save the details first user needs to first pass certain validations i.e email should be of correct format,mobile must be either empty or must be of 10 digits,age must be greater or equal to 13 years.
d) To save the details to the firebase database, click on sign up button.
e) when the details are successfully saved to the firebase database Toast message showing success is shown to the user.

## How to Get this Library
To get a Git project into your build:

### Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
### Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.kumar-sandeep:libraryTest:0.0.3'
	}
  
## How to use this Library
 ### To Start MainActivity Activity
         Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        
 ### To Start Main2Activity Activity
          Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
