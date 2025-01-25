import com.jakewharton.mosaic.layout.height
import com.jakewharton.mosaic.modifier.Modifier
import com.jakewharton.mosaic.runMosaicBlocking
import com.jakewharton.mosaic.ui.Column
import com.jakewharton.mosaic.ui.Spacer
import com.jakewharton.mosaic.ui.Text

fun main() = runMosaicBlocking {
    Column {
        Spacer(modifier = Modifier.height(1))
        Text("Hello welcome to the TUI")
    }
}