echo starting build process..
set /P user_name=Enter GIT repo User Name : 
mkdir git-checkout
cd git-checkout
git clone https://github.com/arungangadharan/LockerApp.git
cd LockerApp
mvn clean install
