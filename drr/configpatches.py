#!/usr/bin/python
import sys, os, subprocess,fnmatch, shutil, csv,re, datetime

def travFolder(dir):
   listdirs = os.listdir(dir)
   for f in listdirs:
       pattern = 'patch*.patch'
       if os.path.isfile(os.path.join(dir, f)):
                if fnmatch.fnmatch(f, pattern):
                        print f    
                        filename=os.path.splitext(f)[0]
                        arraynames=filename.split("-")
                        projectId=arraynames[1] 
                        bugId=arraynames[2]
                        toolId=arraynames[3] 
                        bugfolder="./patches/D_correct/"+projectId+bugId
                        if not os.path.exists(bugfolder):
                                os.system("mkdir "+bugfolder) 
                        patchfolder=bugfolder+"/"+filename
                        if not os.path.exists(patchfolder):
                                os.system("mkdir "+patchfolder) 
                        #copy the patch first                       
                        shutil.copy(os.path.join(dir, f), patchfolder+"/") 
                        #create delta.txt file
                        targetfile=""
                        linenumber=""
                        with open("./metadata.csv") as metadata:
                                lines = metadata.readlines()
                                for line in lines:
                                        data=line.split(",")
                                        if projectId==data[0]:
                                                if bugId==data[1]:
                                                        targetfile=data[2].split('/')[-1]
                                                        linenumber=data[3].replace("\n","")


                        if targetfile=="":
                                print projectId+bugId+" has NO INFO!!"

                        with open(patchfolder+"/delta.txt","w") as delta:
                                delta.write("/drr"+bugfolder.split(".")[-1]+"/bug/"+targetfile+":"+linenumber+",1\n")
                                delta.write("/drr"+patchfolder.split(".")[-1]+"/patch/"+targetfile+":"+linenumber+",1")


                        

       else:
           if '.DS_Store' not in f:
                travFolder(dir+'/'+f)

                
if __name__ == '__main__':
        travFolder("./D_correct_DS")

