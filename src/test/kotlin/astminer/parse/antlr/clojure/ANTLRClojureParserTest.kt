package astminer.parse.antlr.clojure

import org.junit.Assert
import org.junit.Test
import java.io.File
import java.io.FileInputStream

class ANTLRClojureParserTest {

    @Test
    fun testNodeIsNotNull() {
        val parser = ClojureParser()
        val file = File("testData/examples/1.clj")

        val node = parser.parse(FileInputStream(file))
        Assert.assertNotNull("Parse tree for a valid file should not be null", node)
    }

    @Test
    fun testProjectParsing() {
        val parser = ClojureParser()
        val projectRoot = File("testData/examples")
        val trees = parser.parseWithExtension(projectRoot, "clj")
        Assert.assertEquals("There is only 1 file with .clj extension in 'testData/examples' folder",1, trees.size)
        trees.forEach { Assert.assertNotNull("Parse tree for a valid file should not be null", it) }
    }
}
