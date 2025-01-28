package com.BiteBooking.backend;


import com.BiteBooking.backend.model.*;
import com.BiteBooking.backend.repository.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Main.class, args);

		// Repositorios
		RestaurantRepository restaurantRepository = context.getBean(RestaurantRepository.class);
		MenuRepository menuRepository = context.getBean(MenuRepository.class);
		UserRepository userRepository = context.getBean(UserRepository.class);
		BookingRepository bookingRepository = context.getBean(BookingRepository.class);
		DishRepository dishRepository = context.getBean(DishRepository.class);
		RatingRepository ratingRepository = context.getBean(RatingRepository.class);

		PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
		User user1 = new User(null,"Jehiel"," Linarez",LocalDate.now(), "jehiel@bitefood.es", passwordEncoder.encode("admin1234"), "123456789", Role.USER,"https://i.ibb.co/gzMWwzK/profile.jpg", "Madrid", "I love Food");
		User user2 = new User(null,"Carlos"," Feliz",LocalDate.now(), "carlos@bitefood.es", passwordEncoder.encode("admin1234"),"123456789", Role.ADMIN,"https://i.ibb.co/kKh10Bg/1682683426487.jpg", "Madrid", null);
		User user3 = new User(null,"Gandalf","The white wizard.",LocalDate.now(), "gandalf@bitefood.es", passwordEncoder.encode("admin1234"),"639965587", Role.ADMIN,"https://i.ibb.co/zF2sWWf/gandalf2.jpg", "Tierra media", "The last grey.");
		User user4 = new User(null, "Maria Laura", "Asuaje Palmero", LocalDate.of(1991,5,10), "malauraasuaje@bitefood.es", passwordEncoder.encode("admin1234"), "622139560", Role.ADMIN, "", "Madrid", "The Cookies Queen " );
		userRepository.saveAll(List.of(user1,user2, user3, user4));

		// Creacion de  Restaurant
		Restaurant restaurant = new Restaurant(null, "Oh-Toro", "64335241", RestaurantType.JAPANESE_FOOD,"descripcion aqui", LocalTime.now(), LocalTime.now(),  true,"https://i.ibb.co/JnNWhhp/oh-toro.jpg","Madrid","calle Asuncion 55", "1", "28005",4.5, null,user1);
		Restaurant restaurant1 = new Restaurant(null, "Maguro", "65634578", RestaurantType.AMERICAN_FOOD,"descripcion aqui", LocalTime.now(), LocalTime.now(),  true,"https://images.otstatic.com/prod/25860960/1/large.jpg","Madrid","calle ave maria 45", "1", "28005",4.5, null,user2);
		Restaurant restaurant2 = new Restaurant(null, "Slvj", "656345123", RestaurantType.BAR,"descripcion aqui", LocalTime.now(), LocalTime.now(),  false,"https://slvj.es/restaurantes/madridcanalejas.jpg","Madrid","calle palomar 15", "1", "28005",4.5, null, user3);
		Restaurant restaurant3 = new Restaurant(null, "Isaki", "656345123", RestaurantType.AFRICAN_FOOD,"descripcion aqui", LocalTime.now(), LocalTime.now(), false,"https://panama.degustamenu.com/uploads/restorants/fa51e105-3bc7-4004-9ddb-38d0c574d5c8_large.jpg","Madrid","calle Corazon de maria 45", "1", "28006",4.5, null, user4);
		Restaurant restaurant4 = new Restaurant(null, "Zuma", "64335124", RestaurantType.ARABIAN_FOOD,"descripcion aqui", LocalTime.now(), LocalTime.now(), true,"https://zumarestaurant.com/wp-content/uploads/2023/06/best-Japanese-restaurant-in-mykonos.jpg","Madrid","calle Aguero 45", "1", "28006",4.5, null,user2);

		restaurantRepository.saveAll(List.of(restaurant,restaurant1, restaurant2, restaurant3,restaurant4));


		//Creación de un menú


		Menu menu1 = new Menu(null, "Omakase santoryu", "Japones","https://www.onlyyouhotels.com/content/imgsxml/galerias/panel_galeriarestauracion/1/1836.jpg", true, RestaurantType.JAPANESE_FOOD, true, restaurant4);
		Menu menu2 = new Menu(null, "Isakaya Fest", "Japones","https://d28dpoj42hxr8c.cloudfront.net/files/user/201803091609_12.jpg?v=1520579395", true, RestaurantType.JAPANESE_FOOD, true, restaurant1);

		Menu menu3 = new Menu(null, "Mexican Night", "Mexicana","https://media.istockphoto.com/id/1414641728/photo/tacos-al-pastor-from-mexico.jpg?s=612x612&w=0&k=20&c=63ySV1BbYIL4vUAeexISGIw5shY4TzxCtVP9Z33mucM=", true, RestaurantType.TEX_MEX_FOOD, true, restaurant3);
		Menu menu4 = new Menu(null, "Brunch Madrileño", "Brunch","https://offloadmedia.feverup.com/madridsecreto.co/wp-content/uploads/2020/02/20104952/mejores-brunches-madrid-marieta.jpg", true, RestaurantType.BRUNCH, true,restaurant3);
		Menu menu5 = new Menu(null, "2 Worlds", "Fusion", "https://www.7canibales.com/wp-content/uploads/sites/2/2022/06/Nigiri-de-bogavante-1-400x296.jpg", true, RestaurantType.FUSION_FOOD, true, restaurant3);
		Menu menu6 = new Menu(null, "Toledo olvidado", "Española","https://regalaivancerdeño.es/wp-content/uploads/2023/01/Menu-Toledo-Olvidado.jpg", true, RestaurantType.SPAIN_FOOD, true, restaurant2);
		Menu menu7 = new Menu(null, "Vietnam Experiences", "Vietnamita","https://res.cloudinary.com/rainforest-cruises/images/c_fill,g_auto/f_auto,q_auto/w_1120,h_650/v1622728135/The-Best-Food-In-Vietnam-Pho/The-Best-Food-In-Vietnam-Pho.jpg", true, RestaurantType.VIETNAM_FOOD, true, restaurant3);
		Menu menu8 = new Menu(null, "GastroTap", "Fusion", "https://estaticos.esmadrid.com/cdn/farfuture/_Xjt0-7oPc_QhFzHnKMWUBGuG4z9AEpS2LsonR6wSHs/mtime:1646730092/sites/default/files/styles/content_type_full/public/recursosturisticos/restaurantes/jl1_1426675355.278.jpg?itok=AB5_23QG", true, RestaurantType.FUSION_FOOD, true, restaurant);
		Menu menu9 = new Menu(null, "Todos Santos", "Mixology","https://madriddiferente.com/wp-content/uploads/2022/01/SANTOS-Y-DESAMPARADOS-coctel-Dragon-amarillo.jpg", true, RestaurantType.BAR, false, restaurant1);
		Menu menu10 = new Menu(null, "Tropicalisimo", "Panameña","https://www.ongvoluntariado.org/wp-content/uploads/2020/02/Voluntariado-Panama-y-Guatemala.jpg", true, RestaurantType.LATIN_AMERICAN_FOOD, true, restaurant3);
		Menu menu11 = new Menu(null, "The last dance", "American", "https://estaticos-cdn.prensaiberica.es/clip/7a7956c4-d1d7-4fbb-8205-ca9a92d36318_16-9-discover-aspect-ratio_default_0.jpg", true, RestaurantType.AMERICAN_FOOD, true, restaurant2);
		Menu menu12 = new Menu(null, "Cuzco Rocks", "Peruana","https://res.cloudinary.com/rainforest-cruises/images/c_fill,g_auto/f_auto,q_auto/w_1120,h_732,c_fill,g_auto/v1625762943/Peruvian-Food-Main-Image/Peruvian-Food-Main-Image-1120x732.jpg", true, RestaurantType.PERUVIAN_FOOD, true, restaurant);
		Menu menu13 = new Menu(null, "Hot pot Fest", "Zeshuan","https://eatandlovemadrid.es/files/wordpress/wp-content/uploads/2019/05/nunca-cocines.jpg", true, RestaurantType.CHINESE_FOOD, true, restaurant);

		Menu menu14 = new Menu(null, "Empanadisima", "Argentina","https://i.blogs.es/0d79fc/captura-de-pantalla-2019-10-18-a-la-s-13.03.51/650_1200.png", true, RestaurantType.ARGENTINE_FOOD, true, restaurant);
		Menu menu15 = new Menu(null, "Africa Roots", "Africana","https://res-2.cloudinary.com/hpwmsw17c/image/upload/q_auto/v1/ghost-blog-images/Exploring-the-Delicious-and-Diverse-World-of-African-Cuisine.jpg", true, RestaurantType.AFRICAN_FOOD, true, restaurant);
		Menu menu16 = new Menu(null, "Sweet Dreams", "Bakery", "https://www.texmexrestaurante.com/wp-content/uploads/go-x/u/c4ad8fa5-a099-4fdc-b1b9-ce7038d6d6dd/l0,t0,w1080,h1080/image-768x768.png", true, RestaurantType.LATIN_AMERICAN_FOOD, true, restaurant);
		Menu menu17 = new Menu(null, "La de la abuela", "Mexicana","https://regalaivancerdeño.es/wp-content/uploads/2023/01/Menu-Toledo-Olvidado.jpg", true, RestaurantType.TEX_MEX_FOOD, true, restaurant3);
		Menu menu18 = new Menu(null, "Vietnam Experiences", "Vietnamita","https://res.cloudinary.com/rainforest-cruises/images/c_fill,g_auto/f_auto,q_auto/w_1120,h_650/v1622728135/The-Best-Food-In-Vietnam-Pho/The-Best-Food-In-Vietnam-Pho.jpg", true, RestaurantType.VIETNAM_FOOD, true, restaurant2);
		Menu menu19 = new Menu(null, "GastroTap", "Fusion", "https://estaticos.esmadrid.com/cdn/farfuture/_Xjt0-7oPc_QhFzHnKMWUBGuG4z9AEpS2LsonR6wSHs/mtime:1646730092/sites/default/files/styles/content_type_full/public/recursosturisticos/restaurantes/jl1_1426675355.278.jpg?itok=AB5_23QG", true, RestaurantType.AMERICAN_FOOD, true, restaurant);
		Menu menu20 = new Menu(null, "Todos Santos", "Mixology","https://madriddiferente.com/wp-content/uploads/2022/01/SANTOS-Y-DESAMPARADOS-coctel-Dragon-amarillo.jpg", true, RestaurantType.BAR, true, restaurant);
		Menu menu21 = new Menu(null, "Tropicalisimo", "Panameña","https://www.ongvoluntariado.org/wp-content/uploads/2020/02/Voluntariado-Panama-y-Guatemala.jpg", true, RestaurantType.LATIN_AMERICAN_FOOD, true, restaurant);
		Menu menu22 = new Menu(null, "The last dance", "American", "https://estaticos-cdn.prensaiberica.es/clip/7a7956c4-d1d7-4fbb-8205-ca9a92d36318_16-9-discover-aspect-ratio_default_0.jpg", true, RestaurantType.AMERICAN_FOOD, true, restaurant);
		menuRepository.saveAll(List.of(menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8, menu9, menu10, menu11, menu12, menu13, menu14, menu15, menu16, menu17, menu18,menu19, menu20,menu21, menu22));

		//RATINGS
		Rating R1 = new Rating(null, 4,"Comentaario de prueba1", menu1, user1);
		Rating R2 = new Rating(null, 4,"Comentaario de prueba2", menu2, user2);
		Rating R3 = new Rating(null, 4,"Comentaario de prueba3", menu3, user3);
		Rating R4 = new Rating(null, 4,"Comentaario de prueba4", menu4, user4);
		Rating R5 = new Rating(null, 4,"Comentaario de prueba5", menu5, user4);
		Rating R6 = new Rating(null, 4,"Comentaario de prueba6", menu6, user3);
		Rating R7 = new Rating(null, 4,"Comentaario de prueba7", menu7, user4);
		Rating R8 = new Rating(null, 4,"Comentaario de prueba8", menu8, user2);
		Rating R9 = new Rating(null, 3,"Comentaario de prueba9", menu9, user3);
		Rating R10 = new Rating(null, 4,"Comentaario de prueba10", menu10, user1);
		Rating R11 = new Rating(null, 4,"Comentaario de prueba11", menu11, user3);
		Rating R12 = new Rating(null, 4,"Comentaario de prueba12", menu12, user2);
		Rating R13 = new Rating(null, 4,"Comentaario de prueba", menu13, user4);
		Rating R14 = new Rating(null, 4,"Comentaario de prueba", menu14, user3);
		Rating R15 = new Rating(null, 4,"Comentaario de prueba", menu15, user2);
		Rating R16= new Rating(null, 4,"Comentaario de prueba", menu16, user1);
		Rating R17 = new Rating(null, 4,"Comentaario de prueba", menu17, user3);
		Rating R18 = new Rating(null, 4,"Comentaario de prueba", menu18, user3);
		Rating R19 = new Rating(null, 4,"Comentaario de prueba", menu19, user1);
		Rating R20 = new Rating(null, 4,"¡Qué delicia! Este risotto de champiñones es simplemente divino. Los sabores se mezclan de manera perfecta, ¡una obra maestra culinaria!", menu20, user1);
		Rating R21 = new Rating(null, 4,"¡No puedo resistirme a este exquisito sushi de salmón! La frescura del pescado y la textura del arroz son simplemente incomparables. ¡Una experiencia gastronómica inolvidable!", menu21, user2);
		Rating R22 = new Rating(null, 4,"¡Este menu de paella me transporta directamente a la costa española! Los mariscos frescos y el arroz perfectamente sazonado hacen que cada bocado sea una explosión de sabor y tradición.", menu1, user3);
		Rating R23 = new Rating(null, 4,"¡Increíble el sabor de este filete de ternera! Jugoso, tierno y lleno de sabor. Definitivamente, una elección ganadora para los amantes de la carne.", menu2, user4);
		Rating R24 = new Rating(null, 4,"¡El aroma y sabor de este curry tailandés son simplemente adictivos! La combinación de especias y la cremosidad del curry hacen que sea una experiencia culinaria única.", menu3, user3);
		Rating R25 = new Rating(null, 4,"¡Qué placer para el paladar este menu de tacos al pastor! La carne marinada con las especias adecuadas y la frescura de la piña hacen que cada bocado sea una explosión de sabor mexicano.", menu4, user1);
		Rating R26 = new Rating(null, 4,"¡Esta lasaña de vegetales es simplemente celestial! Las capas de verduras frescas, salsa de tomate y queso derretido se combinan a la perfección en cada porción.", menu5, user2);
		Rating R27 = new Rating(null, 4,"¡No puedo tener suficiente de este menu de ramen! Los fideos perfectamente cocidos, el caldo aromático y los trozos de carne tierna hacen que sea una verdadera delicia para el alma.", menu6, user3);
		Rating R28 = new Rating(null, 4,"¡Este ceviche peruano es una obra maestra de frescura y sabor! El pescado marinado en limón con cilantro y cebolla roja es simplemente perfecto para refrescar el paladar.", menu7, user4);
		Rating R29 = new Rating(null, 4,"¡Qué placer disfrutar de este menu de falafel! Crujiente por fuera, suave por dentro y acompañado de una salsa de tahini deliciosa. ¡Una explosión de sabor mediterráneo!", menu8, user3);
		Rating R30 = new Rating(null, 4,"¡El sabor ahumado de esta barbacoa de costillas es simplemente incomparable! La carne se deshace en la boca y la salsa barbecue es la combinación perfecta de dulce y picante.", menu9, user1);
		Rating R31 = new Rating(null, 4,"¡Este menu de pescado a la plancha es una verdadera delicia para los amantes de la cocina saludable! Fresco, ligero y lleno de sabor natural. ¡Una opción perfecta para una comida balanceada!", menu10, user1);
		Rating R32 = new Rating(null, 4,"¡El aroma de esta pizza recién salida del horno es simplemente irresistible! El queso derretido, los ingredientes frescos y la masa crujiente hacen que cada bocado sea una experiencia culinaria inolvidable.", menu11, user2);
		Rating R33 = new Rating(null, 4,"¡No puedo dejar de elogiar este menu de sushi de anguila! La anguila a la parrilla con la dulzura de la salsa teriyaki es simplemente celestial. ¡Una verdadera obra maestra japonesa!", menu12, user3);
		Rating R34 = new Rating(null, 4,"¡Este menu de pollo al curry es una explosión de sabor en cada bocado! El pollo tierno, las verduras frescas y la mezcla perfecta de especias hacen que sea una experiencia culinaria auténtica.", menu13, user4);
		Rating R35 = new Rating(null, 4,"¡Qué placer es disfrutar de esta tabla de quesos! La variedad de quesos, acompañados de frutos secos y mermeladas caseras, hacen que cada bocado sea una verdadera fiesta para el paladar.", menu14, user1);
		Rating R36 = new Rating(null, 4,"¡Este menu de huevos benedictinos es la forma perfecta de empezar el día! Los huevos pochados, la salsa holandesa y el pan inglés tostado crean una combinación de sabores y texturas simplemente deliciosa.", menu15, user1);
		Rating R37= new Rating(null, 4,"¡Las empanadas argentinas son simplemente irresistibles! El relleno jugoso de carne, las aceitunas y las especias hacen que cada bocado sea una explosión de sabor latinoamericano.", menu16, user1);
		Rating R38 = new Rating(null, 4,"¡Este menu de pasta carbonara es simplemente exquisito! La pasta al dente, la salsa cremosa de huevo y panceta crujiente hacen que sea una verdadera delicia italiana.", menu17, user2);
		Rating R39 = new Rating(null, 4,"¡Este menu de salmón a la parrilla es una verdadera joya culinaria! El salmón fresco, la piel crujiente y el toque de limón hacen que cada bocado sea una experiencia gastronómica memorable.", menu18, user3);
		Rating R40 = new Rating(null, 4,"¡Este menu de salmón a la parrilla es una verdadera joya culinaria! El salmón fresco, la piel crujiente y el toque de limón hacen que cada bocado sea una experiencia gastronómica memorable.", menu19, user4);
		Rating R41 = new Rating(null, 4,"¡Este menu de salmón a la parrilla es una verdadera joya culinaria! El salmón fresco, la piel crujiente y el toque de limón hacen que cada bocado sea una experiencia gastronómica memorable.", menu20, user1);
		Rating R42 = new Rating(null, 4,"¡Este menu de salmón a la parrilla es una verdadera joya culinaria! El salmón fresco, la piel crujiente y el toque de limón hacen que cada bocado sea una experiencia gastronómica memorable.", menu21, user1);
		ratingRepository.saveAll(List.of(R1,R2,R3,R4,R5,R6,R7,R8,R9,
				R10,R11,R12,R13,R14,R15,R16,R17,R18,R19,
				R20,R21,R22,R23, R24, R25, R26, R27, R28, R29,R30, R31, R32, R33, R34, R35, R36, R37, R38, R39, R40, R41, R42));

		// Creación de una reserva
		// restaurant = restaurantRepository.findById(restaurant.getId()).orElseThrow();
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user1, 4,"Sin observaciones", false, true, 12, restaurant, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user2, 5,"Sin observaciones", true, false, 412, restaurant1, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user3, 3,"Sin observaciones", false, true, 444, restaurant2, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user4, 9,"Sin observaciones", true, false, 44, restaurant3, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user1, 5,"Sin observaciones", false, true, 46, restaurant4, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user2, 7,"Sin observaciones", true, false, 12, restaurant1, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user1, 1,"Sin observaciones", false, true, 69, restaurant2, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user2, 4,"Sin observaciones", true, false, 99, restaurant3, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user3, 9,"Sin observaciones", false, true, 77, restaurant4, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user4, 2,"Sin observaciones", true, false, 88, restaurant4, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user3, 3,"Sin observaciones", false, false, 99, restaurant3, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user4, 6,"Sin observaciones", true, true, 101, restaurant2, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user1, 9,"Sin observaciones", false, false, 404, restaurant1, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user2, 4,"Sin observaciones", true, true, 901, restaurant1, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user3, 5,"Sin observaciones", false, false, 101, restaurant2, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user4, 32,"Sin observaciones", true, true, 51, restaurant1, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user3, 21,"Sin observaciones", false, false, 1231, restaurant2, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user2, 10,"Sin observaciones", true, true,323, restaurant3, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user1, 32,"Sin observaciones", false, false, 121, restaurant1, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user2, 12,"Sin observaciones", true, true, 33, restaurant1, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user3, 12,"Sin observaciones", false, false, 123, restaurant2, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user4, 31,"Sin observaciones", true, true, 123, restaurant2, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user1, 23,"Sin observaciones", false, false, 123, restaurant1, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user2, 41,"Sin observaciones", true, true, 213, restaurant3, ""));


		//Crear Dish






	}
}