#!/bin/bash

car=(highlander camry corolla)
echo $car       #print first element in array
echo ${car[*]}	#print all of the contents in the array
x="welcome to 60-256"
for i in $x
	do
			echo $i #prints each word in the string list
	done

for i in $x
	do
			echo '$i' : $i; #single quote stops interpretation, its just the characters
	done

x=(welcome to 60-256) #this loop will only print welcome (as scene from $car, you'll only get the first element)
for i in $x
	do
			echo $i; #single quote stops interpretation, its just the characters
	done

for i in ${x[*]} #this time it'll print every word in the array
	do
			echo $i;
	done
	
mylist=`ls`
for i in $mylist	#will print every element in the directory (mylist is not an array, its a string)
	do
		echo $i
	done

for i in $mylist[*];	#ls gives a string, so we get confused between array and string
	do
		echo $i;
	done
#final output will be apple\norange[*] ([*] gets concatenated with orange)


mylist=(`ls`)	#store the result of ls in an array
for i in $mylist[*];	#proper way to loop through an array
	do
		echo $i;
	done
#final output will be apple[*] (concatenate the first element in the array with [*], because its not in iteration notation)

#lets say we have 100 photos on our desktop and a gif
#we want to make a folder to move only photos into the directory
#mylist=(`ls`)
#filename=DSC001.gif
#echo ${filename/DSC/photo1-}	#string operation that concatenates photo1- with DSC001.gif, cutting out DSC

#mylist=(`ls DSC.gif`)	#list all files with DSC.gif extension
#use mv filename to move the filename to a director
#for filename in ${mylist[*]}
#	do
#		mv $filename myphotos ${filename/DSC/photo1-}
#	done

#now we want to list all directories first, and then list all the files

#newlist=(`ls -l`)

#for filename in ....
#	do
#		[-d $filename]
#		[-f $filename]
#	done

#get the wc of a file without using wc
wc -w fruits.sh #this would output the numberOfWords fruits.sh
x=(welcome to 60256)
echo ${#x[*]} #this would print 3
#words=(`more apple`) ; echo ${#words[*]}	#this should work too

#bash as a programming language
#function name(){ #this is how to make a functino in bash

#}
myecho() {
	echo study hard
}
myecho
myecho

myecho(){
	echo $1
}

myecho study
myecho hard

myecho(){
	echo $1
}
echo $1 #would echo the first argument when the script is called, not referencing the function

ls(){
	echo hello world
}
ls #redefinition of ls
local x #this is a local variable, it can only be changed in its scope (which will probably be inside a functino or loop)
[$# != 2]; #how many command line arguments given, if it is not 2
tr = translate
-d #means delete 
tr -d #(translate but delete the old string)

###########################################################
#File Access Permission
#Permission on the file vs permission on the directory
#	Say you have read permission on the file, trying to type ls
#		What permission do you need to use ls?
#		 Cannot ls in a directory without read permission on 

Implement ls for a directory
	opendir(filename) (where filename is either the current directory, or from the command line argument)

	DIR *dp
	dr = opendir("./")
	readdir(dr) will print one item after another



