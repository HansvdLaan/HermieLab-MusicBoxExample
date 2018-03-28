package sample.hermielab;

import java.lang.String;
import java.util.HashSet;
import java.util.Set;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.NotFoundException;

public class ApplicationInjector {
	private Set<String> observedClasses;

	public ApplicationInjector() {
		observedClasses = new HashSet();
		fillObservedClassSet();
	}

	private void fillObservedClassSet() {
		observedClasses.add("sample.Controller");
		observedClasses.add("sample.Main");
	}

	public void inject() throws NotFoundException, CannotCompileException {
		ClassPool pool = ClassPool.getDefault();
		for(String clazz: observedClasses) {
//			pool.importPackage("sample");
//			CtClass ctClass = pool.get(clazz);
//			for(CtConstructor constructor: ctClass.getConstructors()) {
//				constructor.insertAfter("{instancemanager.InstanceManager.getInstance().addClassInstance($0);}");
//			}
//			ctClass.toClass();
		}
	}
}
