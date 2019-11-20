package com.paradisehell.compiler;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@SupportedAnnotationTypes(value = { "com.paradisehell.api.annotation.SubscribeOnMainThread" })
@SupportedOptions(value = { "POST_OBJECT_ON_MAIN_THREAD_INDEX" })
public final class AnnotationCompiler extends AbstractProcessor {
  //<editor-fold desc="常量">
  private static final String POST_OBJECT_ON_MAIN_THREAD_INDEX = "POST_OBJECT_ON_MAIN_THREAD_INDEX";
  //</editor-fold>

  //<editor-fold desc="方法重写">

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }
  //</editor-fold>

  //<editor-fold desc="方法实现">

  @Override
  public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
    try {
      String index = processingEnv.getOptions().get(POST_OBJECT_ON_MAIN_THREAD_INDEX);
      processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, index);
      if (index != null) {
        int indexPackageLastIndex = index.lastIndexOf(".");
        String indexPackage = null;
        if (indexPackageLastIndex != -1) {
          indexPackage = index.substring(0, indexPackageLastIndex);
        }
        Writer writer = processingEnv.getFiler().createSourceFile(index).openWriter();
        if (indexPackage != null) {
          writer.write("package " + indexPackage + ";\n");
        }
        writer.close();
        return true;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }
  //</editor-fold>
}
