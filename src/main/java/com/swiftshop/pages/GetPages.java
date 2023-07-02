/**
 * 
 */
package com.swiftshop.pages;

public class GetPages {

	private LoginPage loginPage = null;
	private HomePage homePage = null;
	private SuperBarPage superBarPage = null;
	private TrolleyAndCheckoutPage trolleyAndCheckoutPage = null;
	private DeliverySlotPage deliverySlotPage = null;
	private ChooseDeliveryTimePage chooseDeliveryTimePage = null;
	private MyAccountPage myAccountPage = null;
	private AlmostDonePage almostDonePage = null;
	private RegistrationPage registrationPage = null;
	private HelpAndSupportPage helpAndSupportPage = null;
	private PaymentPage paymentPage = null;
	private SubscriptionPage subscriptionPage = null;
	private OrderConfirmationPage orderConfirmationPage = null;
	private OrdersPage ordersPage = null;
	private RecipeToOrderPage recipeToOrderPage = null;

	public LoginPage getLoginPage() {
		if (loginPage == null) {
			return new LoginPage();
		} else {
			return loginPage;
		}
	}

	public HomePage getHomePage() {
		if (homePage == null) {
			return new HomePage();
		} else {
			return homePage;
		}
	}

	public SuperBarPage getSuperBarPage() {
		if (superBarPage == null) {
			return new SuperBarPage();
		} else {
			return superBarPage;
		}
	}

	public TrolleyAndCheckoutPage getTrolleyAndCheckoutPage() {
		if (trolleyAndCheckoutPage == null) {
			return new TrolleyAndCheckoutPage();
		} else {
			return trolleyAndCheckoutPage;
		}
	}

	public DeliverySlotPage getDeliverySlotPage() {
		if (deliverySlotPage == null) {
			return new DeliverySlotPage();
		} else {
			return deliverySlotPage;
		}
	}

	public ChooseDeliveryTimePage getChooseDeliveryTimePage() {
		if (chooseDeliveryTimePage == null) {
			return new ChooseDeliveryTimePage();
		} else {
			return chooseDeliveryTimePage;
		}
	}

	public MyAccountPage myAccountPage() {
		if (myAccountPage == null) {
			return new MyAccountPage();
		} else {
			return myAccountPage;
		}
	}

	public RegistrationPage getRegistrationPage() {
		if (registrationPage == null) {
			return new RegistrationPage();
		} else {
			return registrationPage;
		}
	}

	public HelpAndSupportPage getHelpAndSupportPage() {
		if (helpAndSupportPage == null) {
			return new HelpAndSupportPage();
		} else {
			return helpAndSupportPage;
		}
	}

	public PaymentPage getPaymentPage() {
		if (paymentPage == null) {
			return new PaymentPage();
		} else {
			return paymentPage;
		}
	}

	public SubscriptionPage getSubscriptionPage() {
		if (subscriptionPage == null) {
			return new SubscriptionPage();
		} else {
			return subscriptionPage;
		}
	}

	public OrderConfirmationPage getOrderConfirmationPage() {
		if (orderConfirmationPage == null) {
			return new OrderConfirmationPage();
		} else {
			return orderConfirmationPage;
		}
	}

	public OrdersPage getOrdersPage() {
		if (orderConfirmationPage == null) {
			return new OrdersPage();
		} else {
			return ordersPage;
		}
	}

	public RecipeToOrderPage getRecipeToOrderPage() {
		if (orderConfirmationPage == null) {
			return new RecipeToOrderPage();
		} else {
			return recipeToOrderPage;
		}
	}

	public AlmostDonePage getAlmostDonePage() {
		if (almostDonePage == null) {
			return new AlmostDonePage();
		} else {
			return almostDonePage;
		}
	}
}
