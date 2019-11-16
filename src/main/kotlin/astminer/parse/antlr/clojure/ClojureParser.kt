package astminer.parse.antlr.clojure

import me.vovak.antlr.parser.ClojureLexer
import me.vovak.antlr.parser.ClojureParser
import astminer.common.model.Parser
import astminer.parse.antlr.SimpleNode
import astminer.parse.antlr.convertAntlrTree
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.InputStream
import java.lang.Exception

class ClojureParser : Parser<SimpleNode> {
    override fun parse(content: InputStream): SimpleNode? {
        return try {
            val lexer = ClojureLexer(CharStreams.fromStream(content))
            lexer.removeErrorListeners()
            val tokens = CommonTokenStream(lexer)
            val parser = ClojureParser(tokens)
            parser.removeErrorListeners()
            val context = parser.file()
            convertAntlrTree(context, ClojureParser.ruleNames, ClojureParser.VOCABULARY)
        } catch (e: Exception) {
            return null
        }
    }
}
