//App

    Están definidas tres entidades de Dominio Account/Payment/PaymentBean

    Tenemos dos Recursos:

        - RequestPayment: (Solicitud de Pago con varios Pagos)

              *** Cómo observarás hay clases que terminan en Mult, son de este recurso , porque causa confusión con el otro(Payment)

              PaymentRequestRestController: tuve que usar @requestMapping(), porque cuando empecé con los Test , me tiraba error por eso

              *POST
                Está terminado:
                    Cuando llega al Controller, procesa el PaymentRequestDto y lo pasa PaymentRequestBean -> CreatePaymentRequestUseCase
                    CreatePaymentRequestUseCase:
                        PaymentRequestBean contiene List<PaymentRequestDetailBean>, entonces proceso la request por un lado y los pagos por otro
                        Validaciones: Lo tengo dividido en dos ; pero en esta opción he usado las dos
                        Son Predicates, que los unifico en Uno sólo, si pasa la validación ok() y si no Devuelve un error y Genera un DomainException

                        ***Yo no se si he hecho bien mezclando los validadores, porque aqui uso también los de Payment

                        ***Lo que he hecho también  es mappear cada Detail a payment y utilizar el recurso Payment



              *GET -> No está terminado

        - Payment: Generar un Pago

              *POST
                Está terminado, tiene casi el mismo flujo que PaymentRequest
                Para generar un pago, tiene que pasar unas validaciones,si pasa la validación , lo crea y si no DomainException, dependiendo el filtro
                que no haya pasado, el flujo se detiene en el primer error, igual para PaymentRequest

****

//Test

    Unit

    Integración :

       -TestContainer + Docker -> Nombre+DIT

       -Mockmvc/ResTemplate -> NombreIT

       -DataJpaTest-> Probar basicamente JPA

       -Cucumber -> No me reconoce la Configuración y No he podido hacerlo yo;


//EVENTOS
    Lo de PaymentEventPort, es sin kafka , estaba probando una manera de hacerlo
    Kafka -> Tengo algo creado, pero no hay nada testeado, ni probado por eso está comentado en el usecase de payment,
                porfa revisa un poco las clases a ver como voy
//Mapper
    MapStructs - Están separados según su uso
            Para Dto - Bean: En Application
            Para Ben-Entity: En Infraestrcutura