package infrastructure.builder;

import application.auth.IAuth;
import application.auth.IToken;
import application.auth.user.IUserRepository;
import application.chat.IChat;
import application.chat.ISender;
import application.shop.IShop;
import application.shop.medicine.IMedicineRepository;
import infrastructure.builder.Build.Built;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

public class Builder {

	@Inject @Default
	private IAuth auth;

	@Inject @Default
	private IUserRepository userRepository;

	@Inject @Default
	private IToken tokenService;

	@Inject @Default
	private IShop shop;

	@Inject @Default
	private IMedicineRepository medicineRepository;

	@Inject @Default
	private IChat chat;

	@Inject @Default
	private ISender chatService;

	@Produces @Built
	public IAuth buildAuthorizable(){
		auth.setUserRepository(userRepository);
		auth.setTokenService(tokenService);
		return auth;
	}
	 
	@Produces @Built
	public IShop buildShopable(){
		shop.setMedicineRepository(medicineRepository);
		return shop;
	}

	@Produces @Built
	public IChat buildChatable(){
		chat.setChatService(chatService);
		return chat;
	}
}
