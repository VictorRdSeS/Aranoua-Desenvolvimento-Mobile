package ifam.com.jokenposheldoncooper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    var some : Animation? = null
    var aparece : Animation? = null
    var papel = R.drawable.papel
    var pedra = R.drawable.pedra
    var tesoura = R.drawable.tesoura
    var spok = R.drawable.spock
    var lagarto = R.drawable.lagarto
    var num_jogada01: Int = 0
    var num_jogada02: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        some = AlphaAnimation(1.0f,0.0f)
        some?.duration = 1000
        aparece = AlphaAnimation(0.0f,1.0f)
        aparece?.duration = 250

        //implementar a interface que controla a animacao
        some?.setAnimationListener(object :  Animation.AnimationListener {
            override fun onAnimationStart(aninacao: Animation?) {
                jogador01.visibility = View.VISIBLE
                jogador02.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animacao: Animation?) {
                jogador01.visibility = View.INVISIBLE
                jogador02.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animacao: Animation?) {
                TODO("Not yet implemented")
            }

        })

        aparece?.setAnimationListener(object :  Animation.AnimationListener {
            override fun onAnimationStart(aninacao: Animation?) {
                jogador01.visibility = View.INVISIBLE
                jogador02.visibility = View.INVISIBLE
            }

            override fun onAnimationEnd(animacao: Animation?) {
                jogador01.visibility = View.VISIBLE
                jogador02.visibility = View.VISIBLE
            }

            override fun onAnimationRepeat(animacao: Animation?) {
                TODO("Not yet implemented")
            }
        })
    }
    fun animacao(view: View){
        jogador01.startAnimation(some)
        jogador02.startAnimation(some)
        val tempo: Long = 1000
        Handler().postDelayed({
            tocouBotao(view)
        }, tempo)
    }
    fun tocouBotao(view: View) {
        jogador01.startAnimation(aparece)
        jogador02.startAnimation(aparece)
        //Setando a Imagem do Jogador 01
        if (view.id == botaoPapel.id) {
            jogador01.setImageResource(papel);
            num_jogada01 = 0
            jogar(num_jogada01)
        } else {
            if (view.id == botaoTesoura.id) {
                jogador01.setImageResource(tesoura);
                num_jogada01 = 1
                jogar(num_jogada01)
            } else {
                if (view.id == botaoPedra.id) {
                    jogador01.setImageResource(pedra);
                    num_jogada01 = 2
                    jogar(num_jogada01)
                } else {
                    if (view.id == botaoSpok.id) {
                        jogador01.setImageResource(spok);
                        num_jogada01 = 3
                        jogar(num_jogada01)
                    } else {
                        if (view.id == botaoLagarto.id) {
                            jogador01.setImageResource(lagarto);
                            num_jogada01 = 4
                            jogar(num_jogada01)
                        }
                    }
                }
            }
        }
    }

    fun jogar(num_jogada01: Int) {
        num_jogada02 = (0..4).random()

        //Setando a escolha do Jogador 2
        if (num_jogada02 == 0) {
            jogador02.setImageResource(R.drawable.papel);
            resultado(num_jogada01,num_jogada02)
        } else {
            if (num_jogada02 == 1) {
                jogador02.setImageResource(R.drawable.tesoura);
                resultado(num_jogada01,num_jogada02)
            } else {
                if (num_jogada02 == 2) {
                    jogador02.setImageResource(R.drawable.pedra);
                    resultado(num_jogada01,num_jogada02)
                } else {
                    if (num_jogada02 == 3) {
                        jogador02.setImageResource(R.drawable.spock);
                        resultado(num_jogada01,num_jogada02)
                    } else {
                        jogador02.setImageResource(R.drawable.lagarto);
                        resultado(num_jogada01,num_jogada02)
                    }
                }
            }
        }
    }

    fun resultado(num_jogada01: Int, num_jogada02: Int){
        //Gerando Resultado
        if (num_jogada01 == num_jogada02) {
            toast("Deu empate")
            newGame()
        } else {
            //Resultados quando escolhe papel
            if (num_jogada01 == 0) {
                if (num_jogada02 == 1 || num_jogada02 == 4) {
                    toast("Voce Perdeu!")
                    newGame()
                } else {
                    toast("Voce Ganhou!")
                    newGame()
                }
            } else {
                if (num_jogada01 == 1) {
                    if (num_jogada02 == 2 || num_jogada02 == 3) {
                        toast("Voce Perdeu!")
                        newGame()
                    } else {
                        toast("Voce Ganhou!")
                        newGame()
                    }
                } else {
                    if (num_jogada01 == 2) {
                        if (num_jogada02 == 0 || num_jogada02 == 3) {
                            toast("Voce Perdeu!")
                            newGame()
                        } else {
                            toast("Voce Ganhou!")
                            newGame()
                        }
                    }else{
                        if (num_jogada01 == 3) {
                            if (num_jogada02 == 0 || num_jogada02 == 4) {
                                toast("Voce Perdeu!")
                                newGame()
                            } else {
                                toast("Voce Ganhou!")
                                newGame()
                            }
                        }else{
                            if (num_jogada01 == 4) {
                                if (num_jogada02 == 1 || num_jogada02 == 2) {
                                    toast("Voce Perdeu!")
                                    newGame()
                                } else {
                                    toast("Voce Ganhou!")
                                    newGame()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun newGame() {
        val tempo: Long = 2500
        Handler().postDelayed({
            jogador01.setImageResource(R.drawable.interrogacao)
            jogador02.setImageResource(R.drawable.interrogacao)
        }, tempo)
    }
}