package global.logic.bci.test;

import spock.lang.Specification

class MathTestSpec extends Specification {
	def "first test"() {
		when:
			def result = new MathTest().suma(2,3);
		then:
			result == 5
	}
}