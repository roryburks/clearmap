package clearmapJvm

import sguiSwing.hybrid.MDebug
import java.io.IOException
import java.util.*

interface IScriptService {
    fun loadScript( scriptName: String) : String
}

class JClassScriptService : IScriptService {
    override fun loadScript(scriptName: String): String {
        try {
            var ret = ""
            JClassScriptService::class.java.classLoader.getResource(scriptName).openStream().use {
                val scanner = Scanner(it)
                scanner.useDelimiter("\\A")
                ret = scanner.next()
            }
            return ret
        }catch( e: IOException) {
            MDebug.handleError(MDebug.ErrorType.RESOURCE, "Couldn't load shader script file: [$scriptName]", e)
            return ""
        }
    }
}
