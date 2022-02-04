package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

    static int carId1, carId2;

    @Autowired
    CarRepository carRepository;

    @BeforeEach
    void setUp() {
        carId1 = carRepository.save(new Car("VW", "Y6", "2600")).getId();

        carId2 = carRepository.save(new Car("Tesla", "X", "5200")).getId();
    }

    @Test
    public void testCount(){
        assertEquals(2, carRepository.count());
    }

    @Test
    public void testAddCar(){
        Car carNew = carRepository.save(new Car("Tesla", "S", "4600"));
        assertNotEquals(0, carNew.getId());
        assertEquals(3,carRepository.count());
    }

/*
    @Test
    public void testGetById(){

        Car cFound = carRepository.findById(Integer.toString(carId1)).orElse(null);
        assertEquals("Y6", cFound.getModel());
    }

 */

}