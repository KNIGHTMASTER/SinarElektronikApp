/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wissensalt.sinarelektronik.masterdata.report.core;

/**
 *
 * @author Fauzi
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.URL;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class DirectPrint {   
	/*
	 * Below is properties from Endy Muhardin Source Endy Muhardin |
	 * http://endy.artivisi.com
	 */

    private static final char ESC = (char) 27;

	// ganti kertas
    private static final char[] FORM_FEED = { (char) 12 };

	// reset setting
    private static final char[] RESET = { ESC, '@' };

	// huruf tebal diaktifkan
    public static final char[] BOLD_ON = { ESC, 'E' };

	// huruf tebal dimatikan
    private static final char[] BOLD_OFF = { ESC, 'F' };

	// huruf miring diaktifkan
    public static final char[] ITALIC_ON = { ESC, '4' };

	// huruf miring dimatikan
    private static final char[] ITALIC_OFF = { ESC, '5' };

	// mode draft diaktifkan
    private static final char[] MODE_DRAFT = { ESC, 'x', 0 };
    private static final char[] MODE_NLQ = { ESC, 'x', 1 };
	// font Roman (halaman 47)
    private static final char[] FONT_ROMAN = { ESC, 'k', 0 };

	// font Sans serif
    private static final char[] FONT_SANS_SERIF = { ESC, 'k', 1 };

	// font size (halaman 49)
    private static final char[] SIZE_5_CPI = { ESC, 'W', '1', ESC, 'P' };
    private static final char[] SIZE_6_CPI = { ESC, 'W', '1', ESC, 'M' };
    private static final char[] SIZE_10_CPI = { ESC, 'P' };
    private static final char[] SIZE_12_CPI = { ESC, 'M' };
	// font height
    private static final char[] HEIGHT_NORMAL = { ESC, 'w', '0' };
    private static final char[] HEIGHT_DOUBLE = { ESC, 'w', '1' };
	// double strike (satu dot dicetak 2 kali)
    private static final char[] DOUBLE_STRIKE_ON = { ESC, 'G' };
    private static final char[] DOUBLE_STRIKE_OFF = { ESC, 'H' };

	// http://www.berklix.com/~jhs/standards/escapes.epson
	// condensed (huruf kurus)
    private static final char[] CONDENSED_ON = { (char) 15 };
    private static final char[] CONDENSED_OFF = { (char) 18 };

	// condensed (huruf gemuk)
    private static final char[] ENLARGED_ON = { (char) 14 };
    private static final char[] ENLARGED_OFF = { (char) 20 };

	// line spacing
    private static final char[] SPACING_9_72 = { ESC, '0' };
    private static final char[] SPACING_7_72 = { ESC, '1' };
    private static final char[] SPACING_12_72 = { ESC, '2' };

	// set unit for margin setting
    private static final char[] UNIT_1_360 = { ESC, (char) 40, 'U', '1', '0' };

	// move vertical print position
    private static final char[] VERTICAL_PRINT_POSITION = { ESC, 'J', '1' };

    private static boolean escp24pin; // boolean to indicate whether
	// the printer is a 24 pin esc/p2 epson

    private static int MAX_ADVANCE_9PIN = 216; // for 24/48 pin esc/p2
	// printers this should be 180

    private static int MAX_ADVANCE_24PIN = 180;
    private static int MAX_UNITS = 127; // for vertical positioning
	// range is between 0 - 255 (0 <= n <= 255) according to epson ref. but
	// 255 gives weird errors at 1.5f, 127 as max (0 - 128) seems to be
	// working

    private static final float CM_PER_INCH = 2.54f;
	/*
	 * Printer Name you can set printer by call set method
	 */
    private String printerName;

	/*
	 * IP Address where printer installed Default IP = 127.0.0.1 (localhost) you
	 * can set this property by call set method
	 */
    private String ipAddress = "127.0.0.1";

	/*
	 * Print Service
	 */
    private PrintService printService;

	/*
	 * Default space from left the margin
	 */
    private String defaultSpace = "    ";

	/*
	 * Menggabungkan ipAddress dengan PrinterName ex: \\127.0.0.1\EPSONLX300
	 */
    private String printerDevice;

    public DirectPrint() {
        setPrinterName();
    }
    
    public DirectPrint(String printerName){
        setPrinterName(printerName);
    }
    
	/*
	 * Writer Enter for goto next line
	 */
    private void writeGotoNextLine(Writer writer) throws IOException {
        if (writer != null)
            writer.write('\n');
    }    
	/*
	 * Write to printer with default style
	 */
    public void directPrintFromFile(String filePath) throws IOException {
        String printer = getPrinter();
        FileWriter writer = new FileWriter(printer);
        writer.write(ESC);
	writer.write('C');
	writer.write(23);
		// clear style
        clearStyle(writer);

	File fl = new File(filePath);
	FileReader freader = new FileReader(fl);
	BufferedReader bfreader = new BufferedReader(freader);

	String content = "";
	while ((content = bfreader.readLine()) != null) {
            writer.write(content);
            writeGotoNextLine(writer);
        }
            writer.close();
	}

	/*
	 * Write to printer with default style
	 */
    public void directPrintFromUrl(String url) throws IOException {
        String printer = getPrinter();
	FileWriter writer = new FileWriter(printer);

        writer.write(ESC);
        writer.write('C');
	writer.write(23);

		// clear style
	clearStyle(writer);

	URL uri = new URL(url);
	BufferedReader bfreader = new BufferedReader(new InputStreamReader(uri.openStream()));
        String content = "";
	while ((content = bfreader.readLine()) != null) {
            writer.write(content);
            writeGotoNextLine(writer);
	}

            writer.close();
	}

	/*
	 * Write to printer with default style
	 */
    public void directPrint(String label) throws IOException {
        String printer = getPrinter();
	FileWriter writer = new FileWriter(printer);

	writer.write(ESC);
	writer.write('C');
	writer.write(23);

		// clear style
	clearStyle(writer);
        writer.write(defaultSpace + label);
        writer.close();
    }

	/*
	 * Write to printer with one style
	 */
    public void directPrint(String label, char[] style) throws IOException {
        String printer = getPrinter();
	FileWriter writer = new FileWriter(printer);
        writer.write(ESC);
	writer.write('C');
	writer.write(23);
		// clear style
        clearStyle(writer);
	writer.write(style);
        writer.write(defaultSpace + label);
        writer.close();
    }

	/*
	 * Write to printer with many style
	 */
    public void directPrint(String label, char[][] style) throws IOException {
        String printer = getPrinter();
	FileWriter writer = new FileWriter(printer);
        writer.write(ESC);
	writer.write('C');
	writer.write(23);
		// clear style
        clearStyle(writer);
        for (int i = 0; i < style.length; i++) {
            writer.write(style[i]);
	}
        writer.write(defaultSpace + label);
        writer.close();
    }

	/*
	 * Clear BOLD AND ITALIC style
	 */
    private void clearStyle(FileWriter writer) {
        if (writer != null) {
            try {
                writer.write(BOLD_OFF);
		writer.write(ITALIC_OFF);
            } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }
	}
    }

	/*
	 * Combine IP Address and Printer Name for direct to printer
	 */
    private String getPrinter() {
    boolean printerDeviceSet = false;
            if (printerDevice != null) {
                if (printerDevice.length() > 0)
                printerDeviceSet = true;
            }
            if (!printerDeviceSet)
                return "\\\\" + ipAddress + "\\" + printerName;
		else
		return printerDevice;
            }

	/*
	 * Set printer name with default printer if no printerName is set
	 */
    private void setPrinterName() {
    boolean printerInstalled = false;
    printService = PrintServiceLookup.lookupDefaultPrintService();
        if (printerName != null) {
            if (printerName.length() > 0) {
            printerInstalled = true;
            }
	}
	if (!printerInstalled)
            printerName = printService.getName();
	}
	/*
	 * Get and Set Method
	 * --------------------------------------------------------
	 * ----------------------
	 */

    public String getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPrinterDevice() {
        return printerDevice;
    }

    public void setPrinterDevice(String printerDevice) {
        this.printerDevice = printerDevice;
    }    
}
