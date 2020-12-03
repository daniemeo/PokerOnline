package it.pokeronline.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.pokeronline.model.tavolo.Tavolo;
import it.pokeronline.util.Util;

public class TavoloDTO {
	private Long id;
	private String expMin;
	private String cifraMin;
	private String denominazione;
	public String dataCreazione;

	public TavoloDTO() {
	}

	public TavoloDTO(Long id, String expMin, String cifraMin, String denominazione) {
		this.id = id;
		this.expMin = expMin;
		this.cifraMin = cifraMin;
		this.denominazione = denominazione;
	}

	public TavoloDTO(String expMin, String cifraMin, String denominazione) {
		this.expMin = expMin;
		this.cifraMin = cifraMin;
		this.denominazione = denominazione;
	}

	public TavoloDTO(String expMin, String cifraMin, String denominazione, String dataCreazione) {
		this.expMin = expMin;
		this.cifraMin = cifraMin;
		this.denominazione = denominazione;
		this.dataCreazione = dataCreazione;
	}

	public TavoloDTO( String denominazione,String data, String cifraMin, boolean search ) {
		this.denominazione = denominazione;
		this.dataCreazione = data;
		this.cifraMin = cifraMin;
	}
	public String getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExpMin() {
		return expMin;
	}

	public void setExpMin(String expMin) {
		this.expMin = expMin;
	}

	public String getCifraMin() {
		return cifraMin;
	}

	public void setCifraMin(String cifraMin) {
		this.cifraMin = cifraMin;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public List<String> errors() {
		List<String> result = new ArrayList<String>();
		if (StringUtils.isBlank(this.expMin)) {
			result.add("Il campo Esperienza Minima non può essere vuoto");
		} else if (Long.parseLong(this.expMin) < 0) {
			result.add("Il campo Esperienza Minima non può essere negativo");
		}
		if (StringUtils.isBlank(this.cifraMin)) {
			result.add("Il campo Cifra Minima non può essere vuoto");

		} else if (Double.parseDouble(this.cifraMin) < 0) {
			result.add("Il campo Cifra Minima non può essere negativo");
		}
		if (StringUtils.isBlank(this.denominazione))
			result.add("Il campo Denominazione non può essere vuoto");
		return result;
	}

	public static Tavolo buildModelFromDtoPerSearch(TavoloDTO tavoloDTO) {
		Tavolo result = new Tavolo();

		result.setId(tavoloDTO.getId());
		Long expMinima = null;
		if (!StringUtils.isBlank(tavoloDTO.getExpMin())) {
			expMinima = Long.parseLong(tavoloDTO.getExpMin());
		}
		result.setExpMin(expMinima);

		Integer cifraMin = null;

		if (!StringUtils.isBlank(tavoloDTO.getCifraMin())) {
			cifraMin = Integer.parseInt(tavoloDTO.getCifraMin());
		}
		result.setCifraMin(cifraMin);

		Date data = null;
		if (tavoloDTO.getDataCreazione() != null) {
			try {
				data = new SimpleDateFormat("yyyy-MM-dd").parse(tavoloDTO.getDataCreazione());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		result.setDataCreazione(data);

		result.setDenominazione(tavoloDTO.getDenominazione());
		return result;
	}

	public static Tavolo buildModelFromDto(TavoloDTO tavoloDTO) {
		Tavolo result = new Tavolo();
		result.setId(tavoloDTO.getId());
		result.setDenominazione(tavoloDTO.getDenominazione());
		result.setExpMin(Long.parseLong(tavoloDTO.getExpMin()));
		result.setCifraMin(Integer.parseInt(tavoloDTO.getCifraMin()));
		return result;
	}

	public List<String> errorSearch() {

		List<String> result = new ArrayList<String>();

		if (!StringUtils.isBlank(this.expMin) && Long.parseLong(this.expMin) < 0) {
			if (!Util.isLong(this.expMin)) {
				result.add("il campo esperienza minima non è valido!!");
			}
		}

		if (this.cifraMin != null && !this.cifraMin.isEmpty()) {
			if (!Util.isInteger(this.cifraMin)) {
				result.add("il campo puntata minima non è valido!");
			}
		}
		if (this.dataCreazione != null && !this.dataCreazione.isEmpty()) {
			if (!Util.isDate(this.dataCreazione)) {
				result.add("il campo data non è valido!!");
			}
		}

		return result;

	}

	public List<String> errorSearchPartita() {

		List<String> result = new ArrayList<String>();
		if (this.dataCreazione != null && !this.dataCreazione.isEmpty()) {
			if (!Util.isDate(this.dataCreazione)) {
				result.add("il campo data non è valido!!");
			}
		}
		if (this.cifraMin != null && !this.cifraMin.isEmpty()) {
			if (!Util.isInteger(this.cifraMin)) {
				result.add("il campo puntata minima non è valido!");
			}
		}
		return result;
	}

	public static Tavolo buildModelFromDtoPerSearchPartita(TavoloDTO tavoloDTO) {
		Tavolo result = new Tavolo();

		result.setDenominazione(tavoloDTO.getDenominazione());
		Date data = null;
		if (tavoloDTO.getDataCreazione() != null) {
			try {
				data = new SimpleDateFormat("yyyy-MM-dd").parse(tavoloDTO.getDataCreazione());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		result.setDataCreazione(data);
		Integer cifraMin = null;

		if (!StringUtils.isBlank(tavoloDTO.getCifraMin())) {
			cifraMin = Integer.parseInt(tavoloDTO.getCifraMin());
		}
		result.setCifraMin(cifraMin);
		return result;
	}
	
}
