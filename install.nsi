!include LogicLib.nsh

# name the installer
OutFile "Client_Install.exe"

Name "Home Server Application"

# define the directory to install to, the desktop in this case as specified  
# by the predefined $DESKTOP variable
InstallDir $DESKTOP

# default section start; every NSIS script has at least one section.
Section
    SetOutPath $INSTDIR
    File target\client-1.0.jar
    File .env
    MessageBox MB_OK "This application requires java 19 to be installed."
# default section end
SectionEnd