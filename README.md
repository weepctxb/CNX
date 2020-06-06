# CNX
Chemical Engineering Calculations made simpler.

![CNX](./app/src/main/res/GooglePlayStore.png =400x400)

Quick install: Download [the apk](https://github.com/weepctxb/CNX/tree/master/app/build/outputs/apk/debug), 
[enable third-party apps](https://www.cnet.com/how-to/how-to-enable-third-party-app-installation-on-most-android-phones/) 
on your Android phone, and [install manually](https://www.androidauthority.com/how-to-install-apks-31494/).

A detailed user guide containing available calculation modes can be 
[found here](https://github.com/weepctxb/CNX/blob/master/app/src/main/assets/User%20Guide.pdf)

##FAQ:

Q1: What are the calculation features available in this app?

A: There is a wide variety of calculations available, randing from thermodynamics 
to mechanics. The aim of this app is to greatly simplify and automate many of 
these complex and tedious calculation processes. More features will be added 
over time so stay tuned for more updates.

Q2: What unit systems are being used in this app?

A: This app only uses SI-base units and SI-derived units. 
Tap on the input parameter texts and a popup message will specify the required 
units (for example, the pressure input fields in the virial EoS calculations 
are required to be in bar), and all preset parameters are given in the specified 
units. However for some features (such as Antoine calculations if saturation 
pressure remains in natural logarithm) it is possible to use different units 
if you have your own custom parameter inputs.

Q3: What do the symbols in the different calculation features represent?

A: Tap on the input parameter texts and a popup message will 
include more information (for example B0 and B1 in virial EoS calculations 
refer to the Pitzer correlation parameters).

Q4: What are preset compounds?

A: In many of these calculation features we have preset 
compounds that you can choose from. The required parameters will be 
automatically filled in by the app (for example, selecting a preset 
compounds will autofill the corresponding Antoine parameters for vapour 
pressure calculations via the Antoine equation.

Provided by CNX v0.2.1 &copy; EPCT, 2018-2020