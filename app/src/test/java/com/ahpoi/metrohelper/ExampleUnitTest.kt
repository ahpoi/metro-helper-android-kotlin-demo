package com.ahpoi.metrohelper

import com.ahpoi.metrohelper.fixture.defaultAppUser
import org.junit.Test

import org.junit.Assert.*

class ExampleUnitTest {

    @Test
    fun `should equal to app user`() {
        assertEquals(defaultAppUser().copy("123"), defaultAppUser().copy("123"))
    }

    @Test
    fun `should not equal to app user`() {
        val user1 = defaultAppUser().copy("123")
        val user2 = defaultAppUser().copy(id = "123", firstName = "Rick") //Using copy method with named parameters toLocation simulate the builder pattern
        assertNotEquals(user1, user2)
    }

    @Test
    fun `equality kotlin`() {
        //No need to use .equals, just need to use ==
        val structuralEquality = (defaultAppUser() == defaultAppUser())
        assertTrue(structuralEquality)

        val referentialEquality = (defaultAppUser() === defaultAppUser())
        assertFalse(referentialEquality)
    }

}
