package br.com.status.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.status.domain.Andamento;
import br.com.status.domain.Processo;
import br.com.status.domain.TipoAndamento;
import br.com.status.repository.AndamentoRepository;

@Service
public class TJService {

	private String path = "/home/vitor/tj/";

	@Autowired
	private ProcessoService processoService;

	@Autowired
	private AndamentoRepository andamentoRepository;

	public Andamento salvarAtualizacao(String numeroProcesso) {
		Processo processo = processoService.getProcessoByNumero(numeroProcesso);
		if (processo != null) {
			String atualizacao = buscarAtualizacaoPorProcesso(numeroProcesso);
			if (atualizacao != null) {
				Andamento andamento = new Andamento();
				andamento.setData(LocalDate.now().minusDays(1));
				andamento.setDescricao(atualizacao);
				andamento.setIdProcesso(processo.getId());
				andamento.setTipo(TipoAndamento.AUTOMATICO);
				
				return andamentoRepository.save(andamento);				
			}
		}

		return null;
	}

	public String buscarAtualizacaoPorProcesso(String numeroProcesso) {
//		long inicio = Calendar.getInstance().getTimeInMillis();
		File file = getArquivoDJ();

		if (file != null) {
			String texto = extraiPDF(file);
			return busca(texto, numeroProcesso);
		}

		return null;
	}

	public File getArquivoDJ() {
		File arquivo = new File(getPath());

		try {
			FileReader fileReader = new FileReader(arquivo);
		} catch (FileNotFoundException e) {
			arquivo = downloadUltimoDj();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return arquivo;
	}

	private String getPath() {
		LocalDate ontem = LocalDate.now().minusDays(1);
		while (ontem.getDayOfWeek().equals(DayOfWeek.SUNDAY) || ontem.getDayOfWeek().equals(DayOfWeek.SATURDAY))
			ontem = ontem.minusDays(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String ontemString = ontem.format(formatter);

		return path + ontemString + ".pdf";
	}

	private File downloadUltimoDj() {
		LocalDate ontem = LocalDate.now().minusDays(1);
		while (ontem.getDayOfWeek().equals(DayOfWeek.SUNDAY) || ontem.getDayOfWeek().equals(DayOfWeek.SATURDAY))
			ontem = ontem.minusDays(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String ontemString = ontem.format(formatter);
		String URL = "https://esaj.tjce.jus.br/cdje/downloadCaderno.do?dtDiario=" + ontemString
				+ "&cdCaderno=2&tpDownload=D";
		URI u = URI.create(URL);
		try (InputStream inputStream = u.toURL().openStream()) {
			File file = new File(getPath());
			copyInputStreamToFile(inputStream, file);
			return file;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private void copyInputStreamToFile(InputStream inputStream, File file) {
		try (FileOutputStream outputStream = new FileOutputStream(file)) {

			int read;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String extraiPDF(File caminho) {
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

	public String busca(String texto, String numeroProcesso) {
		try {
			String parteA = texto.substring(texto.indexOf(numeroProcesso), texto.length());
			String textoBusca = parteA.substring(0, parteA.indexOf("ADV:"));

			return textoBusca.replace("\n", " ");
		} catch (StringIndexOutOfBoundsException e) {
			return null;
		}

	}

}
