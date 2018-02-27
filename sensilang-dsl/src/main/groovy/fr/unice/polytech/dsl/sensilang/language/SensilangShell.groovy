package fr.unice.polytech.dsl.sensilang.language

import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.CompilationCustomizer
import org.codehaus.groovy.control.customizers.ImportCustomizer
import org.codehaus.groovy.control.customizers.SecureASTCustomizer

import java.util.function.Function

class SensilangShell {
    private GroovyShell shell

    private CompilerConfiguration configuration

    private SensilangBinding binding
    private SensilangBaseScript baseScript

    SensilangShell() {
        binding = new SensilangBinding()
        binding.setModel(new SensilangModel(binding))

        configuration = getSensilangConfiguration()
        configuration.setScriptBaseClass("fr.unice.polytech.dsl.sensilang.language.SensilangBaseScript")
        // TODO: might want to get that as from the Java API instead of an hardcoded string

        shell = new GroovyShell(configuration)
    }

    private static CompilerConfiguration getSensilangConfiguration() {
        // Customize that as needed
        def secure = new SecureASTCustomizer()
        secure.with {
            closuresAllowed = true
            methodDefinitionAllowed = true
            importsWhitelist = [
                    'java.lang.*',
                    'java.util.function.Function'
            ]
            staticImportsWhitelist = []
            staticStarImportsWhitelist = []
            constantTypesClassesWhiteList = [
                    int, Integer, Number, Long, String, Object, BigDecimal, Function
            ]
            //classes who are allowed to be receivers of method calls
            receiversClassesWhiteList = [
                    int, Number, Integer, String, Object, Function
            ]
        }

        def configuration = new CompilerConfiguration()
        configuration.addCompilationCustomizers(secure)
        configuration.addCompilationCustomizers(getCompilationCustomizers())

        return configuration
    }

    static CompilationCustomizer getCompilationCustomizers() {
        def importCustomizer = new ImportCustomizer()
        importCustomizer.addImport 'Function', 'java.util.function.Function'

        importCustomizer
    }

    void eval(File scriptFile) {
        Script script = shell.parse(scriptFile)
        binding.setScript(script)
        script.setBinding(binding)
        script.run()
    }
}
