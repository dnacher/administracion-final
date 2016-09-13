package traynotification.notification;

public enum Notifications implements Notification {

	INFORMATION("/vista/imagenes/informationIcon.png", "#2C54AB"),
	NOTICE("/vista/imagenes/informationIcon.png", "#8D9695"),
	SUCCESS("/vista/imagenes/Tick.png", "#009961"),
	WARNING("/vista/imagenes/warningIcon.png", "#E23E0A"),
	ERROR("/vista/imagenes/errorIcon.png", "#CC0033"),
        QUESTION("/vista/imagenes/questionIcon.png", "#CC0033");

	private final String urlResource;
	private final String paintHex;

	Notifications(String urlResource, String paintHex) {
		this.urlResource = urlResource;
		this.paintHex = paintHex;
	}

	public String getURLResource() {
		return urlResource;
	}

	public String getPaintHex() {
		return paintHex;
	}

}
