package translations;

import translations.terms.GeneralTerms;

public class TranslationDemo {
	public static void main(String[] args) {
		new TranslationDemo().start();
	}

	private void start() {
		TranslationProvider translationProvider = new TranslationProvider(GeneralTerms.NAME);
		for (int i = 0; i < 10; i++) {
			System.out.println(translationProvider.fetchGeneral(GeneralTerms.USER) + ": " + i);
			System.out.println(translationProvider.fetchGeneral(GeneralTerms.BIRTHDAY) + ": " + i);
			System.out.println();
		}
	}
}
