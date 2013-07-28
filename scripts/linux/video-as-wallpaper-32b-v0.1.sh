#!/bin/bash
# n00bsonubuntu video as wallpaper script v0.1
# 
echo "this script will download and install all the needed files."
#
echo "Installing mplayer and zenity"
echo "Enter password to continue" 
sudo apt-get install mplayer zenity
#
echo "Changing to Downloads directory"
cd ~/Downloads
#
echo "Downloading video-as-wallpaper-32b.tar.gz file."
wget http://n00bsonubuntu.net/downloads/video-as-wallpaper-32b.tar.gz
#
echo "Unpacking tar.gz file"
#
tar -xvzf video-as-wallpaper-32b.tar.gz
#
echo "Removing downloaded video-as-wallpaper-32b.tar.gz file"
#
sudo rm video-as-wallpaper-32b.tar.gz
# 
echo "Now installing xwinwrapcvs.deb"
#
sudo dpkg -i xwinwrapcvs.deb
#
echo "Now removing the xwinwrapcvs.deb file"
#
sudo rm xwinwrapcvs.deb
#
echo "Now moving video-wallpaper-script file to"
echo "./gnome2/nautilus-scripts folder"
#
sudo mv video-wallpaper-script ~/.gnome2/nautilus-scripts/
#
echo "Now removing video-wallpaper-script file"
echo "All downloaded and extracted files are deleted"
echo "Script by tinuz from http://n00bsonubuntu.net/"

