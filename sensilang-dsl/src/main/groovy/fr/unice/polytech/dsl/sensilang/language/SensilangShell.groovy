package fr.unice.polytech.dsl.sensilang.language

import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.SecureASTCustomizer

class SensilangShell {
    private GroovyShell shell

    private CompilerConfiguration configuration

    private SensilangBinding binding
    private SensilangBaseScript baseScript

    SensilangShell() {
        binding = new SensilangBinding()

        configuration = getSensilangConfiguration()
        configuration.setScriptBaseClass("fr.unice.polytech.dsl.sensilang.language.SensilangBaseScript")
        // TODO might want to get that as from the Java API instead of an hardcoded string

        shell = new GroovyShell(configuration)
    }

    private static CompilerConfiguration getSensilangConfiguration() {
        // Customize that as needed
        def secure = new SecureASTCustomizer()
        secure.with {
            //disallow closure creation
            closuresAllowed = false
            //disallow method definitions
            methodDefinitionAllowed = true
            //empty white list => forbid imports
            importsWhitelist = [
                    'java.lang.*'
            ]
            staticImportsWhitelist = []
            staticStarImportsWhitelist = []
            //language tokens disallowed
//			tokensBlacklist= []
            //language tokens allowed
            tokensWhitelist = []
            //types allowed to be used  (including primitive types)
            constantTypesClassesWhiteList = [
                    int, Integer, Number, Integer.TYPE, String, Object
            ]
            //classes who are allowed to be receivers of method calls
            receiversClassesWhiteList = [
                    int, Number, Integer, String, Object
            ]
        }

        def configuration = new CompilerConfiguration()
        configuration.addCompilationCustomizers(secure)

        return configuration
    }
    
    void eval(File scriptFile) {
        Script script = shell.parse(scriptFile)
        script.run()
    }
}
