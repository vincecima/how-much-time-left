package howmuchtimeleft

import io.dropwizard.Application
import io.dropwizard.setup.Environment
import kotlin.platform.platformStatic

class HowMuchTimeLeftApplication() : Application<HowMuchTimeLeftConfiguration>() {
    companion object {
        platformStatic fun main(args: Array<String>) {
            HowMuchTimeLeftApplication().run(*args)
        }
    }
    override fun run(configuration: HowMuchTimeLeftConfiguration?, environment: Environment?) {

    }
}
