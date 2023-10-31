# HOLO Home Client
This is a personal client project for my home systems to keep track and organize various devices, books and debts, and possibly more things to come.

# Installing
Installing is as simple as running the NSIS file provided with the full release (when that comes about). Before then though, follow these steps.
1. Run mvn package install in the root directory (where the pom.xml file is located)
2. Run the install.nsi script in the NSIS installer application
3. Run the installer application

After that, you will need to change the default-env.env file to .env, and then modify the variables there to connect to the HOLO Home Server.