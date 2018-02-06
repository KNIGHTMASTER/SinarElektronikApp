import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Demonstrasi cara mencetak struk dengan printer dot matrix.
 *
 * Pada contoh kode berikut, kita mencetak langsung ke device printer,
 * tidak melalui print service Operating System (print dialog).
 *
 * Sebelum menjalankan contoh kode berikut,
 * pastikan Anda bisa mencetak langsung melalui command prompt.
 *
 * Contoh : di linux menggunakan printer paralel
 * <pre><code>
 * echo "Hello World" > /dev/lp0
 * </code></pre>
 *
 * Bila Anda mendapatkan pesan error, biasanya karena hal berikut :
 * <ul>
 * <li>Salah menggunakan nama device. <br />
 * Pastikan device /dev/lp0 adalah nama device yang benar.<br />
 * Biasanya terjadi kesalahan di sini bila menggunakan printer USB.
 * </li>
 * <li>Permission user tidak memadai. <br />
 * Setelah memastikan Anda menggunakan device yang benar,
 * periksa permissionnya. <br />
 * Di instalasi Ubuntu standar, /dev/lp0 memiliki permission 660
 * dengan owner root dan group lp. <br />
 * Pastikan user yang digunakan adalah root,
 * atau user biasa yang tergabung ke dalam grup lp
 * </li>
 * </ul>
 *
 * @author endy at artivisi dot com
 */
public class DirecPrintV2 {
    private static final char ESC = (char) 27;

    // ganti kertas
    private static final char[] FORM_FEED = {(char) 12};

    // reset setting
    private static final char[] RESET = {ESC,'@'};

    // huruf tebal diaktifkan
    private static final char[] BOLD_ON = {ESC,'E'};

    // huruf tebal dimatikan
    private static final char[] BOLD_OFF = {ESC,'F'};

    // huruf miring diaktifkan
    private static final char[] ITALIC_ON = {ESC,'4'};

    // huruf miring dimatikan
    private static final char[] ITALIC_OFF = {ESC,'5'};

    // mode draft diaktifkan
    private static final char[] MODE_DRAFT = {ESC,'x',0};
    private static final char[] MODE_NLQ = {ESC,'x',1};

    // font Roman (halaman 47)
    private static final char[] FONT_ROMAN = {ESC,'k',0};

    // font Sans serif
    private static final char[] FONT_SANS_SERIF = {ESC,'k',1};

    // font size (halaman 49)
    private static final char[] SIZE_5_CPI = {ESC,'W','1',ESC,'P'};
    private static final char[] SIZE_6_CPI = {ESC,'W','1',ESC,'M'};
    private static final char[] SIZE_10_CPI = {ESC,'P'};
    private static final char[] SIZE_12_CPI = {ESC,'M'};


    //font height
    private static final char[] HEIGHT_NORMAL = {ESC,'w', '0'};
    private static final char[] HEIGHT_DOUBLE = {ESC,'w', '1'};

    // double strike (satu dot dicetak 2 kali)
    private static final char[] DOUBLE_STRIKE_ON = {ESC,'G'};
    private static final char[] DOUBLE_STRIKE_OFF = {ESC,'H'};

    // http://www.berklix.com/~jhs/standards/escapes.epson
    // condensed (huruf kurus)
    private static final char[] CONDENSED_ON = {(char) 15};
    private static final char[] CONDENSED_OFF = {(char) 18};

    // condensed (huruf gemuk)
    private static final char[] ENLARGED_ON = {(char) 14};
    private static final char[] ENLARGED_OFF = {(char) 20};

    // line spacing
    private static final char[] SPACING_9_72 = {ESC, '0'};
    private static final char[] SPACING_7_72 = {ESC, '1'};
    private static final char[] SPACING_12_72 = {ESC, '2'};

    // set unit for margin setting
    private static final char[] UNIT_1_360 = {ESC, (char)40, 'U', '1', '0'};

    // move vertical print position
    private static final char[] VERTICAL_PRINT_POSITION = {ESC, 'J', '1'};


    public static void main(String[] args) throws IOException {

        //String printer = "/dev/lp0"; // Linux
	String printer = "/dev/usb/lp0";  // Windows

        String message = "Hello World";

        // buat writernya
        FileWriter writer = new FileWriter(printer);
/*
	// set unit 1/3600
	writer.write(ESC);
	writer.write((char)40);
	writer.write('U');
	writer.write(1);
	writer.write(0);
	writer.write(1);

	// ESC ( c 4 0 Set page length
	writer.write(ESC);
	writer.write((char)40);
	writer.write('c');
	writer.write(4);
	writer.write(0);
	writer.write(1); //TH
	writer.write(100); //TL
	writer.write(37); //BH
	writer.write(425); //BL
*/

       // set page length 22
       writer.write(ESC);
       writer.write('C');
       writer.write(23);


        cetakStruk("Draft Sans Serif Condensed", writer,
			MODE_DRAFT,
			FONT_SANS_SERIF,
			CONDENSED_ON,
			SIZE_10_CPI,
			SPACING_12_72);

	sendCommand(RESET, writer);
        writer.close();
    }

    public static void sendCommand(char[] command, Writer writer) throws IOException {
        writer.write(command);
    }

    private static void cetakStruk(String title, FileWriter writer, char[]... mode) throws IOException {
        sendCommand(RESET, writer);

        for (int i = 0; i < mode.length; i++) {
            char[] cmd = mode[i];
            sendCommand(cmd, writer);
        }

        cetakStruk(title,writer);

	sendCommand(VERTICAL_PRINT_POSITION, writer);
    }

    private static void cetakStruk(String title, FileWriter writer) throws FileNotFoundException, IOException {

        String strukFile = "LaporanTagihan.txt";
        BufferedReader reader = new BufferedReader(new FileReader(strukFile));

        String content = "";
        while((content = reader.readLine()) != null) {
            writer.write(content);
            gantiBaris(writer);
        }

        reader.close();
    }

    private static void gantiBaris(Writer writer) throws IOException {
        writer.write("\n");
    }

}
