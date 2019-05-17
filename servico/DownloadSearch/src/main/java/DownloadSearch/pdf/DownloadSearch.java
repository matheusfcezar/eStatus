package DownloadSearch.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class DownloadSearch {

	private static String date;
	private static String URL;
	private static final String FILE_TO = "C:\\Users\\mathe\\Desktop\\eStatus\\DownloadSearch\\download\\TJCE.pdf";

	public static void main(String[] args) {
//To Do: Download PDF
		date = getDateTime();
		try {
			System.out.println("Fazendo download");
			download();
			System.out.println("Download realizado com sucesso :)");
//			To Do: Busca de processo
		} catch (IOException e) {
			System.out.println("Algo deu errado :(");
			System.out.println(" error:" + e.getMessage());
		}

//To Do: Busca e extração
		File caminho = new File(FILE_TO);
		String texto = extraiPDF(caminho);
		busca(texto, "0169302-31.2013.8.06.0001");
	}

	private static String getDateTime() {
		LocalDate date = LocalDate.now().minusDays(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		return date.format(formatter);
	}

	private static void download() throws IOException {
		date = getDateTime();
		URL = "https://esaj.tjce.jus.br/cdje/downloadCaderno.do?dtDiario=" + date + "&cdCaderno=2&tpDownload=D";
		System.out.println(URL);
		URI u = URI.create(URL);
		try (InputStream inputStream = u.toURL().openStream()) {
			File file = new File(FILE_TO);
			copyInputStreamToFile(inputStream, file);
		}
	}

	private static void copyInputStreamToFile(InputStream inputStream, File file) throws IOException {

		try (FileOutputStream outputStream = new FileOutputStream(file)) {

			int read;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		}

	}

	public static String extraiPDF(File caminho) {
		PDDocument pdfDocument = null;

		try {
			pdfDocument = PDDocument.load(caminho);
			PDFTextStripper stripper = new PDFTextStripper();
			String texto = stripper.getText(pdfDocument);

			return texto;

		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (pdfDocument != null)
				try {
					pdfDocument.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
		}

	}
	
	public static void busca(String texto, String numeroProcesso) {
		System.out.println("==============================================================");
		String parteA = texto.substring(texto.indexOf(numeroProcesso), texto.length());
		System.out.println("Iniciando busca");
		System.out.println(parteA.substring(0, parteA.indexOf("ADV:")));
		System.out.println("==============================================================");
		System.out.println("Busca finalizada");
	}

}
