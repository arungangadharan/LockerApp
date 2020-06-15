echo starting build process..
set /P user_name=Enter GIT repo User Name : 
mkdir git-checkout
cd git-checkout
git clone https://github.com/arungangadharan/LockerApp.git
cd LockerApp
mvn clean install

echo [InternetShortcut] >> "%USERPROFILE%\desktop\Lock.url"
echo URL="C:\WINDOWS\NOTEPAD.EXE" >> "%USERPROFILE%\desktop\Lock.url"
echo IconFile="D:\workbox\Lock.ico" >> "%USERPROFILE%\desktop\Lock.url"
echo IconIndex=20 >> "%USERPROFILE%\desktop\Lock.url"
