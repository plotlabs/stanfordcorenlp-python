import jpype
from jpype import *

jvmPath = jpype.getDefaultJVMPath() 
#jpype.startJVM(jvmPath, "-ea", "-Djava.class.path=/Users/vedanshikataria/Documents/practice_python/PlotLabs/wrapper/stanford-corenlp-3.9.1.jar")
#classpath = "stanford-corenlp-3.9.1.jar"
#jpype.startJVM(jvmPath, "-Djava.class.path = %s" % classpath)
jpype.startJVM(jvmPath, "-Djava.class.path=stanford-corenlp-3.9.1.jar")
jpype.java.lang.System.out.println("hello world")

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

#---------------------------------------------------------------------------------------------------------

ie_obj = jpype.JPackage("edu.stanford.nlp.naturalli")
print dir(ie_obj)
w = ie_obj.JpypeTrial
print w().call_try()

jpype.shutdownJVM()