import jpype
from jpype import *

def openIE():
	jvmPath = jpype.getDefaultJVMPath() 
	jpype.startJVM(jvmPath, "-Djava.class.path=../stanford-corenlp-3.9.1.jar")
	ie_obj = jpype.JPackage("edu.stanford.nlp.naturalli")
	w = ie_obj.openIE
	return w(), jpype

if __name__ == "__main__":
	
	jvmPath = jpype.getDefaultJVMPath() 
	print jvmPath
	#jpype.startJVM(jvmPath, "-Djava.class.path=../stanford-corenlp-3.9.1.jar")
	jpype.startJVM(jvmPath)
	ie_obj = jpype.JPackage("edu").stanford.nlp.naturalli
	w = ie_obj.openIE
	print w()

	systemclassloader = jpype.java.lang.ClassLoader.getSystemClassLoader()
	for x in systemclassloader.getURLs():
		print x ,"\n"
		print x.path
		print x.getRef

	arrobj = jpype.JPackage("java").util
	print arrobj
	x = arrobj.ArrayList
	nar = x()
	print dir(nar)
	print nar