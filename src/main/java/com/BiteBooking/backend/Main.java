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


		dishRepository.deleteAll();
		bookingRepository.deleteAll();
		ratingRepository.deleteAll();
		menuRepository.deleteAll();
		restaurantRepository.deleteAll();
		userRepository.deleteAll();
		// Creación de usuarios

		PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
		User user1 = new User(null,"Jehiel"," Linarez",LocalDate.now(), "jehiel@bitebooking.com", passwordEncoder.encode("admin1234"), "123456789", Role.USER,"https://i.ibb.co/gzMWwzK/profile.jpg", "Madrid", "I love Food");
		User user2 = new User(null,"Carlos"," Feliz",LocalDate.now(), "carlos@bitebooking.com", passwordEncoder.encode("admin1234"),"123456789", Role.ADMIN,"https://i.ibb.co/kKh10Bg/1682683426487.jpg", "Madrid", null);
		User user3 = new User(null,"Ronald"," Veliz",LocalDate.now(), "ronald@bitebooking.com", passwordEncoder.encode("admin1234"),"123456789", Role.ADMIN,"https://i.ibb.co/qC4YBcY/Whats-App-Image-2024-04-15-at-22-35-07.jpg", "Madrid", null);
		User user4 = new User(null,"John"," marmolejo",LocalDate.now(), "john@bitebooking.com", passwordEncoder.encode("admin1234"),"123456789", Role.ADMIN,"https://i.ibb.co/YjRBHTd/john.jpg", "Madrid" , null);
		User user5 = new User(null,"usuario de pruebas","Pruebas",LocalDate.now(), "user@bitebooking.com", passwordEncoder.encode("admin1234"),"123456789", Role.RESTAURANT,"https://i.ibb.co/92DWn2d/profile-circle-icon-2048x2048-cqe5466q.png", "Madrid" , null);
		User user6 = new User(null,"Usuario Restaurante","Pruebas",LocalDate.now(), "restaurant@bitebooking.com", passwordEncoder.encode("admin1234"),"123456789", Role.RESTAURANT,"https://i.ibb.co/92DWn2d/profile-circle-icon-2048x2048-cqe5466q.png", "Madrid", null);
		User user7 = new User(null,"Gandalf","The white wizard.",LocalDate.now(), "gandalf@bitebooking.com", passwordEncoder.encode("admin1234"),"639965587", Role.ADMIN,"https://i.ibb.co/zF2sWWf/gandalf2.jpg", "Tierra media", "The last grey.");

		userRepository.saveAll(List.of(user1,user2, user3, user4, user5, user6, user7));

		// Creacion de  Restaurant
		Restaurant restaurant = new Restaurant(null, "Oh-Toro", "64335241", RestaurantType.JAPANESE_FOOD,"descripcion aqui", LocalTime.now(), LocalTime.now(),  true,"https://i.ibb.co/JnNWhhp/oh-toro.jpg","Madrid","calle Asuncion 55", "1", "28005",4.5, null,user7);
		Restaurant restaurant1 = new Restaurant(null, "Maguro", "65634578", RestaurantType.AMERICAN_FOOD,"descripcion aqui", LocalTime.now(), LocalTime.now(),  true,"https://images.otstatic.com/prod/25860960/1/large.jpg","Madrid","calle ave maria 45", "1", "28005",4.5, null,user2);
		Restaurant restaurant2 = new Restaurant(null, "Slvj", "656345123", RestaurantType.BAR,"descripcion aqui", LocalTime.now(), LocalTime.now(),  false,"https://slvj.es/restaurantes/madridcanalejas.jpg","Madrid","calle palomar 15", "1", "28005",4.5, null, user3);
		Restaurant restaurant3 = new Restaurant(null, "Isaki", "656345123", RestaurantType.AFRICAN_FOOD,"descripcion aqui", LocalTime.now(), LocalTime.now(), false,"https://panama.degustamenu.com/uploads/restorants/fa51e105-3bc7-4004-9ddb-38d0c574d5c8_large.jpg","Madrid","calle Corazon de maria 45", "1", "28006",4.5, null, user4);
		Restaurant restaurant4 = new Restaurant(null, "Zuma", "64335124", RestaurantType.ARABIAN_FOOD,"descripcion aqui", LocalTime.now(), LocalTime.now(), true,"https://zumarestaurant.com/wp-content/uploads/2023/06/best-Japanese-restaurant-in-mykonos.jpg","Madrid","calle Aguero 45", "1", "28006",4.5, null,user5);
		Restaurant restaurant5 = new Restaurant(null, "Diverxo","688322", RestaurantType.ASIAN_FOOD,"descripcion aqui", LocalTime.now(), LocalTime.now(), true,"https://s1.elespanol.com/2021/11/19/sociedad/consumo/628448515_215465406_1706x960.jpg","Madrid","calle Pruebas 45", "1", "28006",4.5, null, user6);
		Restaurant restaurant6 = new Restaurant(null, "Doppelganger", "7654531", RestaurantType.SPAIN_FOOD,"descripcion aqui", LocalTime.now(), LocalTime.now(), false,"https://www.tapasmagazine.es/wp-content/uploads/2021/11/Mercado-de-Madrid-doppelganger.jpeg","Madrid","calle Pruebas Varias 65", "1", "28007",4.5, null, user2);
		Restaurant restaurant7 = new Restaurant(null, "Omeraki", "652345629", RestaurantType.BALKAN_FOOD,"descripcion aqui", LocalTime.now(), LocalTime.now(), false,"https://www.esmadrid.com/sites/default/files/styles/content_type_full/public/recursosturisticos/restaurantes/omeraki_5.jpg?itok=MTOLwZtm","Madrid","calle Excelencias 20", "1", "28007",4.5, null, null);
		Restaurant restaurant8 = new Restaurant(null, "Eneko", "64339878", RestaurantType.CANADIAN_FOOD,"descripcion aqui", LocalTime.now(), LocalTime.now(), true,"https://enekoatxa.com/wp-content/uploads/2022/11/debokata-madrid-eneko-atxa.jpg","Madrid","calle Mistica 14", "1", "28007",4.5, null, null);
		Restaurant restaurant9 = new Restaurant(null, "Amazonico", "6556729", RestaurantType.CHINESE_FOOD,"descripcion aqui",LocalTime.now(), LocalTime.now(), true,"https://media-cdn.tripadvisor.com/media/photo-s/1a/85/3a/73/amazonico-london-bar.jpg","Madrid","calle tuerto 12", "1", "28008",4.5, null, null);
		Restaurant restaurant10 = new Restaurant(null, "Ivan Cerdeño", "65123129", RestaurantType.DOMINICAN_FOOD,"descripcion aqui", LocalTime.now(), LocalTime.now(), false,"https://imag.bonviveur.com/sala-del-restaurante-ivan-cerdeno.jpg","Madrid","calle atardecer 12", "1", "28008",4.5, null, null);
		Restaurant restaurant11 = new Restaurant(null, "La Cabra", "656123", RestaurantType.FRENCH_FOOD,"descripcion aqui", LocalTime.now(), LocalTime.now(),false,"https://media-cdn.tripadvisor.com/media/photo-s/16/21/4b/41/la-barra-de-cocteleria.jpg","Madrid","calle Seguridad 20", "1", "28008",4.5, null, null);
		Restaurant restaurant12 = new Restaurant(null, "Starbucks", "643879", RestaurantType.AMERICAN_FOOD,"descripcion aqui", LocalTime.now(), LocalTime.now(), true,"https://a1.eestatic.com/metropoliabierta/2022/03/22/vivir-en-barcelona/659194078_9238993_1706x960.jpg","Madrid","calle Exception 45", "1", "28009",4.5, null, null);
		Restaurant restaurant13 = new Restaurant(null, "Papua", "656212", RestaurantType.AFRICAN_FOOD,"descripcion aqui", LocalTime.now(), LocalTime.now(), true,"https://hips.hearstapps.com/hmg-prod/images/papu-a-colo-n-elle-9-1643149268.jpg","Madrid","calle nuemro 45", "1", "28009",4.5, null, null);
		Restaurant restaurant14 = new Restaurant(null, "Robuchon by Robuchon", "652349", RestaurantType.FRENCH_FOOD,"descripcion aqui", LocalTime.now(), LocalTime.now(), false,"https://media.revistavanityfair.es/photos/64340f56f7f5eb1ea544226c/16:9/w_2560%2Cc_limit/robuchon14647-PERFIL-2.jpg","Madrid","calle mencion", "1", "28005",4.5, null, null);
		Restaurant restaurant15 = new Restaurant(null, "Carbon negro", "656908765", RestaurantType.SPAIN_FOOD,"descripcion aqui", LocalTime.now(), LocalTime.now(), false,"https://unbuendiaenmadrid.com/wp-content/uploads/2018/11/sala.jpg","Madrid","calle Hola mundo 64", "1", "28009",4.5, null, null);
		Restaurant restaurant16 = new Restaurant(null, "Grosso napoletano", "5687713", RestaurantType.ITALIAN_FOOD,"descripcion aqui",LocalTime.now(), LocalTime.now(), true,"https://media-cdn.tripadvisor.com/media/photo-s/0f/39/9f/e9/interior-de-grosso-napoletano.jpg","Madrid","calle Salvacion 62", "1", "28017",4.5, null, null);
		Restaurant restaurant17 = new Restaurant(null, "El bulli", "9865432", RestaurantType.GEORGIAN_FOOD,"descripcion aqui", LocalTime.now(), LocalTime.now(), true,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMrX6u4_eQaINJEyTsKWid16SC_l8DXrnVq-vrBM1kPw&s","Madrid","calle nuemro 45", "1", "28017",4.5, null, null);
		Restaurant restaurant18 = new Restaurant(null, "Can Roca", "98765432", RestaurantType.GREEK_FOOD,"descripcion aqui",LocalTime.now(), LocalTime.now(), false,"https://cellercanroca.com/wp-content/uploads/2023/03/restaurant-img1.jpg","Madrid","calle nuemro 45", "1", "28018",4.5, null, null);
		Restaurant restaurant19 = new Restaurant(null, "StretXO", "876543", RestaurantType.SPAIN_FOOD,"descripcion aqui", LocalTime.now(), LocalTime.now(), false,"https://media-cdn.tripadvisor.com/media/photo-s/19/2d/4e/89/streetxo.jpg","Madrid","calle nuemro 45", "1", "28019",4.5, null, user2);
		Restaurant restaurant20 = new Restaurant(null, "Ritz Mandarin", "65634529", RestaurantType.CHINESE_FOOD,"descripcion aqui", LocalTime.now(), LocalTime.now(), false,"https://phantom-elmundo.unidadeditorial.es/39052cb3fc4e2dc2a4013311cfd24d23/crop/0x175/2045x1538/resize/1200/f/jpg/assets/multimedia/imagenes/2021/04/13/16183207571642.jpg","Madrid","calle nuemro 45", "1", "28025",4.5, null, user2);
		Restaurant restaurant21 = new Restaurant(null, "The Curry House", "652348765", RestaurantType.INDIAN_FOOD, "Auténtica cocina india con especias importadas directamente de India.", LocalTime.now(), LocalTime.now(), true, "https://i.ibb.co/NS6KFLp/ZDIw-Lmpw-Zw.jpg","Madrid","calle nuemro 45", "1", "28015",4.5, null, user2);
		Restaurant restaurant22 = new Restaurant(null, "El Asador Patagónico", "658765432", RestaurantType.ARGENTINE_FOOD, "Especialidades argentinas como asado, empanadas y más.", LocalTime.now(), LocalTime.now(), true, "https://i.ibb.co/f1bvKKC/NGQw-ZDAuan-Bn.jpg","Madrid","calle nuemro 45", "1", "28030",4.5, null, user2);
		Restaurant restaurant23 = new Restaurant(null, "Cafe de Paris", "654328765", RestaurantType.FRENCH_FOOD, "Disfrute de los mejores postres franceses y un café excepcional.", LocalTime.now(), LocalTime.now(), true, "https://i.ibb.co/R40fgJn/bm-Ff-MTguan-Bn.jpg","Madrid","calle nuemro 45", "1", "28031",4.5, null, user2);
		Restaurant restaurant24 = new Restaurant(null, "Santorini Breeze" , "643875432", RestaurantType.GREEK_FOOD, "Sabores frescos del mar Egeo y la tradicional hospitalidad griega.", LocalTime.now(), LocalTime.now(), true, "https://i.ibb.co/t4M4TmV/ODM3-YWUw-MS5qc-Gc.jpg","Madrid","calle nuemro 45", "1", "28035",4.5, null, null);
		Restaurant restaurant25 = new Restaurant(null, "Seoul Kitchen", "632548765", RestaurantType.KOREAN_FOOD, "Cocina coreana contemporánea en un ambiente moderno.", LocalTime.now(), LocalTime.now(), true, "https://i.ibb.co/Jc4HshK/Zw.jpg","Madrid","calle nuemro 45", "1", "28005",4.5, null, user7);
		Restaurant restaurant26 = new Restaurant(null, "Cantina Mexicana", "645678932", RestaurantType.TEX_MEX_FOOD, "Los mejores tacos y margaritas en la ciudad.", LocalTime.now(), LocalTime.now(), true, "https://i.ibb.co/ymTW3fC/YWRvb-Glk-Lmpw-Zw.jpg","Madrid","calle nuemro 45", "1", "28034",4.5, null, user7);
		Restaurant restaurant27 = new Restaurant(null, "Bangkok Street", "654398765", RestaurantType.THAI_FOOD, "Sabores auténticos de la calle de Bangkok con ingredientes frescos todos los días.",LocalTime.now(), LocalTime.now(), true, "https://i.ibb.co/qgLMmWv/cGc.jpg","Madrid","calle nuemro 45", "1", "28034",4.5, null, user7);
		Restaurant restaurant28 = new Restaurant(null, "Pho Real", "623498765", RestaurantType.VIETNAM_FOOD, "Pho, banh mi, y más platos vietnamitas en un ambiente acogedor.",LocalTime.now(), LocalTime.now(), true, "https://i.ibb.co/Kw4msrN/LmpwZw.jpg","Madrid","calle nuemro 45", "1", "28034",4.5, null, user7);
		Restaurant restaurant29 = new Restaurant(null, "Havana Nights", "643298765", RestaurantType.CARIBBEAN_FOOD, "Cocina caribeña vibrante en el corazón de la ciudad.", LocalTime.now(), LocalTime.now(), true, "https://i.ibb.co/XS94fFt/LmpwZw.jpg","Madrid","calle nuemro 45", "1", "28036",4.5, 20, user7);
		Restaurant restaurant30 = new Restaurant(null, "Marrakesh Express", "642398765", RestaurantType.MARRAKECH_FOOD, "Experimente la magia de Marrakech con platos tradicionales marroquíes.", LocalTime.now(), LocalTime.now(), true, "https://i.ibb.co/0fNDPgP/MDAuanBn.jpg","Madrid","calle nuemro 45", "1", "28038",4.5, 20, user2);
		Restaurant restaurant31 = new Restaurant(null, "Lisbon Delights" , "645398012", RestaurantType.PORTUGUESE_FOOD, "Pasteles de nata, bacalhau a bras, y otros platos portugueses clásicos.", LocalTime.now(), LocalTime.now(), true, "https://i.ibb.co/F8cg23f/a-Xpl-PTY0-MDoq.jpg","Madrid","calle nuemro 45", "1", "28038",4.5, 20, user2);
		Restaurant restaurant32 = new Restaurant(null, "Berlin Wall", "659876543", RestaurantType.GERMAN_FOOD, "Cervezas alemanas y la mejor salchicha en tu vida",LocalTime.now(), LocalTime.now(),true,"https://i.ibb.co/ZxDKXcg/PTM2MDoq.jpg","Madrid","calle nuemro 45", "1", "28028",4.5, 20, user2);
		Restaurant restaurant33 = new Restaurant(null, "CafeMania", "659876543", RestaurantType.COFFEE_STORE, "Cafe Insuperable",LocalTime.now(), LocalTime.now(),true,"https://i.ibb.co/dPVZ62V/RGVLVFV3-Sz-A9.jpg","Madrid","calle nuemro 45", "1", "28025",4.5, 20, user2);
		Restaurant restaurant34 = new Restaurant(null, "Brunaterra", "659876543", RestaurantType.BRUNCH, "Los Mejores desayunos de la ciudad",LocalTime.now(), LocalTime.now(),true,"https://i.ibb.co/x2Hq4f8/UT0.jpg","Madrid","calle nuemro 45", "1", "28040",4.5,20, user2);

		restaurantRepository.saveAll(List.of(restaurant,restaurant1, restaurant2, restaurant3,restaurant4,restaurant5,restaurant6,restaurant7,restaurant8,restaurant9,restaurant10,restaurant11,restaurant12,restaurant13,restaurant14, restaurant15, restaurant16, restaurant17,restaurant18, restaurant19, restaurant20,restaurant21,restaurant22,restaurant23,restaurant24,restaurant25,restaurant26,restaurant27,restaurant28,restaurant29,restaurant30,restaurant31,restaurant32,restaurant33,restaurant34));


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
		Rating R5 = new Rating(null, 4,"Comentaario de prueba5", menu5, user5);
		Rating R6 = new Rating(null, 4,"Comentaario de prueba6", menu6, user5);
		Rating R7 = new Rating(null, 4,"Comentaario de prueba7", menu7, user5);
		Rating R8 = new Rating(null, 4,"Comentaario de prueba8", menu8, user5);
		Rating R9 = new Rating(null, 3,"Comentaario de prueba9", menu9, user5);
		Rating R10 = new Rating(null, 4,"Comentaario de prueba10", menu10, user5);
		Rating R11 = new Rating(null, 4,"Comentaario de prueba11", menu11, user5);
		Rating R12 = new Rating(null, 4,"Comentaario de prueba12", menu12, user5);
		Rating R13 = new Rating(null, 4,"Comentaario de prueba", menu13, user5);
		Rating R14 = new Rating(null, 4,"Comentaario de prueba", menu14, user5);
		Rating R15 = new Rating(null, 4,"Comentaario de prueba", menu15, user5);
		Rating R16= new Rating(null, 4,"Comentaario de prueba", menu16, user5);
		Rating R17 = new Rating(null, 4,"Comentaario de prueba", menu17, user5);
		Rating R18 = new Rating(null, 4,"Comentaario de prueba", menu18, user5);
		Rating R19 = new Rating(null, 4,"Comentaario de prueba", menu19, user5);
		Rating R20 = new Rating(null, 4,"¡Qué delicia! Este risotto de champiñones es simplemente divino. Los sabores se mezclan de manera perfecta, ¡una obra maestra culinaria!", menu20, user5);
		Rating R21 = new Rating(null, 4,"¡No puedo resistirme a este exquisito sushi de salmón! La frescura del pescado y la textura del arroz son simplemente incomparables. ¡Una experiencia gastronómica inolvidable!", menu21, user5);
		Rating R22 = new Rating(null, 4,"¡Este menu de paella me transporta directamente a la costa española! Los mariscos frescos y el arroz perfectamente sazonado hacen que cada bocado sea una explosión de sabor y tradición.", menu1, user5);
		Rating R23 = new Rating(null, 4,"¡Increíble el sabor de este filete de ternera! Jugoso, tierno y lleno de sabor. Definitivamente, una elección ganadora para los amantes de la carne.", menu2, user5);
		Rating R24 = new Rating(null, 4,"¡El aroma y sabor de este curry tailandés son simplemente adictivos! La combinación de especias y la cremosidad del curry hacen que sea una experiencia culinaria única.", menu3, user5);
		Rating R25 = new Rating(null, 4,"¡Qué placer para el paladar este menu de tacos al pastor! La carne marinada con las especias adecuadas y la frescura de la piña hacen que cada bocado sea una explosión de sabor mexicano.", menu4, user1);
		Rating R26 = new Rating(null, 4,"¡Esta lasaña de vegetales es simplemente celestial! Las capas de verduras frescas, salsa de tomate y queso derretido se combinan a la perfección en cada porción.", menu5, user2);
		Rating R27 = new Rating(null, 4,"¡No puedo tener suficiente de este menu de ramen! Los fideos perfectamente cocidos, el caldo aromático y los trozos de carne tierna hacen que sea una verdadera delicia para el alma.", menu6, user3);
		Rating R28 = new Rating(null, 4,"¡Este ceviche peruano es una obra maestra de frescura y sabor! El pescado marinado en limón con cilantro y cebolla roja es simplemente perfecto para refrescar el paladar.", menu7, user4);
		Rating R29 = new Rating(null, 4,"¡Qué placer disfrutar de este menu de falafel! Crujiente por fuera, suave por dentro y acompañado de una salsa de tahini deliciosa. ¡Una explosión de sabor mediterráneo!", menu8, user5);
		Rating R30 = new Rating(null, 4,"¡El sabor ahumado de esta barbacoa de costillas es simplemente incomparable! La carne se deshace en la boca y la salsa barbecue es la combinación perfecta de dulce y picante.", menu9, user5);
		Rating R31 = new Rating(null, 4,"¡Este menu de pescado a la plancha es una verdadera delicia para los amantes de la cocina saludable! Fresco, ligero y lleno de sabor natural. ¡Una opción perfecta para una comida balanceada!", menu10, user1);
		Rating R32 = new Rating(null, 4,"¡El aroma de esta pizza recién salida del horno es simplemente irresistible! El queso derretido, los ingredientes frescos y la masa crujiente hacen que cada bocado sea una experiencia culinaria inolvidable.", menu11, user2);
		Rating R33 = new Rating(null, 4,"¡No puedo dejar de elogiar este menu de sushi de anguila! La anguila a la parrilla con la dulzura de la salsa teriyaki es simplemente celestial. ¡Una verdadera obra maestra japonesa!", menu12, user3);
		Rating R34 = new Rating(null, 4,"¡Este menu de pollo al curry es una explosión de sabor en cada bocado! El pollo tierno, las verduras frescas y la mezcla perfecta de especias hacen que sea una experiencia culinaria auténtica.", menu13, user4);
		Rating R35 = new Rating(null, 4,"¡Qué placer es disfrutar de esta tabla de quesos! La variedad de quesos, acompañados de frutos secos y mermeladas caseras, hacen que cada bocado sea una verdadera fiesta para el paladar.", menu14, user5);
		Rating R36 = new Rating(null, 4,"¡Este menu de huevos benedictinos es la forma perfecta de empezar el día! Los huevos pochados, la salsa holandesa y el pan inglés tostado crean una combinación de sabores y texturas simplemente deliciosa.", menu15, user5);
		Rating R37= new Rating(null, 4,"¡Las empanadas argentinas son simplemente irresistibles! El relleno jugoso de carne, las aceitunas y las especias hacen que cada bocado sea una explosión de sabor latinoamericano.", menu16, user1);
		Rating R38 = new Rating(null, 4,"¡Este menu de pasta carbonara es simplemente exquisito! La pasta al dente, la salsa cremosa de huevo y panceta crujiente hacen que sea una verdadera delicia italiana.", menu17, user2);
		Rating R39 = new Rating(null, 4,"¡Este menu de salmón a la parrilla es una verdadera joya culinaria! El salmón fresco, la piel crujiente y el toque de limón hacen que cada bocado sea una experiencia gastronómica memorable.", menu18, user3);
		Rating R40 = new Rating(null, 4,"¡Este menu de salmón a la parrilla es una verdadera joya culinaria! El salmón fresco, la piel crujiente y el toque de limón hacen que cada bocado sea una experiencia gastronómica memorable.", menu19, user4);
		Rating R41 = new Rating(null, 4,"¡Este menu de salmón a la parrilla es una verdadera joya culinaria! El salmón fresco, la piel crujiente y el toque de limón hacen que cada bocado sea una experiencia gastronómica memorable.", menu20, user5);
		Rating R42 = new Rating(null, 4,"¡Este menu de salmón a la parrilla es una verdadera joya culinaria! El salmón fresco, la piel crujiente y el toque de limón hacen que cada bocado sea una experiencia gastronómica memorable.", menu21, user5);
		ratingRepository.saveAll(List.of(R1,R2,R3,R4,R5,R6,R7,R8,R9,
				R10,R11,R12,R13,R14,R15,R16,R17,R18,R19,
				R20,R21,R22,R23, R24, R25, R26, R27, R28, R29,R30, R31, R32, R33, R34, R35, R36, R37, R38, R39, R40, R41, R42));

		// Creación de una reserva
		// restaurant = restaurantRepository.findById(restaurant.getId()).orElseThrow();
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user1, 4,"Sin observaciones", false, true, 12, restaurant, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user2, 5,"Sin observaciones", true, false, 412, restaurant1, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user3, 3,"Sin observaciones", false, true, 444, restaurant2, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user4, 9,"Sin observaciones", true, false, 44, restaurant3, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user5, 5,"Sin observaciones", false, true, 46, restaurant4, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user6, 7,"Sin observaciones", true, false, 12, restaurant5, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user1, 1,"Sin observaciones", false, true, 69, restaurant6, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user2, 4,"Sin observaciones", true, false, 99, restaurant7, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user3, 9,"Sin observaciones", false, true, 77, restaurant8, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user4, 2,"Sin observaciones", true, false, 88, restaurant9, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user5, 3,"Sin observaciones", false, false, 99, restaurant10, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user6, 6,"Sin observaciones", true, true, 101, restaurant11, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user1, 9,"Sin observaciones", false, false, 404, restaurant12, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user2, 4,"Sin observaciones", true, true, 901, restaurant13, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user3, 5,"Sin observaciones", false, false, 101, restaurant14, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user4, 32,"Sin observaciones", true, true, 51, restaurant15, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user5, 21,"Sin observaciones", false, false, 1231, restaurant16, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user6, 10,"Sin observaciones", true, true,323, restaurant17, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user1, 32,"Sin observaciones", false, false, 121, restaurant18, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user2, 12,"Sin observaciones", true, true, 33, restaurant19, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user3, 12,"Sin observaciones", false, false, 123, restaurant20, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user4, 31,"Sin observaciones", true, true, 123, restaurant21, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user5, 23,"Sin observaciones", false, false, 123, restaurant22, ""));
		bookingRepository.save(new Booking(null, LocalDateTime.now(), user6, 41,"Sin observaciones", true, true, 213, restaurant23, ""));


		//Crear Dish

		Dish dish = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese sauced",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu2);
		Dish dish1 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese sauced",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu2);
		Dish dish2 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese saucek",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu3);
		Dish dish3 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese saucel",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu4);
		Dish dish4 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese sauceñ",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu5);
		Dish dish5 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese saucey",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu1);
		Dish dish6 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese saucee",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu1);
		Dish dish7 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese sauce0",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu1);
		Dish dish8 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese sauce1",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu1);
		Dish dish9 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese sauce2",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu1);
		Dish dish10 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese sauce3",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu1);
		Dish dish11 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese sauce4",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu1);
		Dish dish12 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese sauce5",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu1);
		Dish dish13 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese saucej",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu1);
		Dish dish14 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese saucek",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu1);
		Dish dish15 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese sauce6",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu1);
		Dish dish16 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese sauce7",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu1);
		Dish dish17 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese sauce8",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu1);
		Dish dish18 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese sauce9",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu1);
		Dish dish19 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese sauce10",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu1);
		Dish dish20 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese sauce11",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu1);
		Dish dish21 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese sauce",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu1);
		Dish dish22 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese sauce",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu1);
		Dish dish23 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese sauce",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu1);
		Dish dish24 = new Dish(null, "King Crab Yuzu truffle butter", "Fresh King Crab with yuzu and truffle Hollandese sauce",90.00, "https://facefoodmag.com/fotos/recetas/merus-cangrejo-real-salsa-holandesa-yuzu.jpg",true, true,menu1);

		dishRepository.saveAll(List.of(dish,dish1,dish2,dish3,dish4,dish5,dish6,dish7,dish8,dish9,dish10,dish11,dish12,dish13,dish14,dish15,dish16,dish17,dish18,dish19,dish20,dish21,dish22,dish23,dish24));



	}
}