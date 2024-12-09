package com.personalfinancial.entities;

public enum CategoryColor {

	RED("VERMELHO"), BLUE("AZUL"), GREEN("VERDE"), YELLOW("AMARELO"), ORANGE("LARANJA"), PURPLE("ROXO"), BLACK("PRETO"),
	WHITE("BRANCO"), GRAY("CINZA"), PINK("ROSA"), BROWN("MARROM"), CYAN("CIANO"), MAGENTA("MAGENTA"), LIME("LIMÃO"),
	TEAL("AZUL-PETRÓLEO"), NAVY("AZUL-MARINHO"), GOLD("DOURADO"), SILVER("PRATEADO"), BEIGE("BEGE"), IVORY("MARFIM"),
	TURQUOISE("TURQUESA"), MAROON("VINHO"), OLIVE("OLIVA"), AQUA("ÁGUA"), LAVENDER("LAVANDA"), SALMON("SALMÃO"),
	TAN("BRONZEADO"), PEACH("PÊSSEGO");

	String color;

	CategoryColor(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}
}
