package by.dev.madhead.playground.immutables;

import by.dev.madhead.playground.immutables.immutables.*;

import java.util.Date;

public class Main {
	public static void main(String[] args) {
		final Person madhead = ImmutablePerson
				.builder()
				.firstName("Siarhei")
				.lastName("Krukau")
				.age(25)
				.build();

		final Car madheadsCar = ImmutableCar
				.builder()
				.owner(madhead)
				.maxSpeed(110)
				.doorsCount(5)
				.sweptVolume(1.6)
				.build();

		final Person clone = ImmutablePerson.copyOf(madhead).withFirstName("Clone");

		final House house = ImmutableHouse.builder().addTenants(madhead, clone).build();

		System.out.println(madhead);
		System.out.println(madheadsCar);
		System.out.println(house);

//		house.getTenants().remove(0);
//		System.out.println(house);

		final Apple apple = ImmutableApple
				.builder()
				.color("red")
				.weight(0.130D)
				.build();

//      weight is not set
//		final Apple invalidApple = ImmutableApple
//				.builder()
//				.color("red")
//				.build();

		System.out.println(apple);
		System.out.println(apple.getName());

		final Bottle bottle1 = ImmutableBottle
				.builder()
				.material(Bottle.Material.PLASTIC)
				.build();

		final Bottle bottle2 = ImmutableBottle
				.builder()
				.material(Bottle.Material.GLASS)
				.volume(1.5D)
				.sticker("Guinness")
				.productionDate(new Date())
				.build();

		System.out.println(bottle1);
		System.out.println(bottle2);

		final CPU cpu = ImmutableCPU
				.builder()
				.setFrequency(2400)
				.setCoresCount(4)
				.build();

		System.out.println(cpu);

		final Bulb bulb = ImmutableBulb.of(Bulb.BulbType.LED, 6500);
		final Bulb bulbClone1 = ImmutableBulb.of(Bulb.BulbType.LED, 6500);
		final Bulb bulbClone2 = ImmutableBulb.copyOf(bulb);

		System.out.println("Bulbs:");
		System.out.println(bulb);
		System.out.println(bulb == bulbClone1); // false
		System.out.println(bulb == bulbClone2); // true
		System.out.println(bulbClone1 == bulbClone2); // false

		final Pill pill1 = ImmutablePill.of("red");
		final Pill pill2 = ImmutablePill.of("blue");
		final Pill pill3 = ImmutablePill.of("red");

		System.out.println("Pills:");
		System.out.println(pill1 == pill2); // true, interning!
		System.out.println(pill1 == pill3); // false

		final MyAnnotation annotation = ImmutableMyAnnotation
				.builder()
				.enable(true)
				.value("1", "2")
				.build();

		System.out.println(annotation);
	}
}
