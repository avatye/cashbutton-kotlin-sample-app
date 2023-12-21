package com.sudal.cashblock_box.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.avatye.cashblock.unit.cashbox.CashBoxSDK
import com.avatye.cashblock.unit.cashbox.launcher.CashBoxLauncher
import com.avatye.cashblock.unit.cashbox.launcher.ICashBoxLauncher
import com.avatye.cashblock.unit.cashbox.listener.ICashBoxClose
import com.avatye.cashblock.unit.cashbox.listener.ICashBoxList
import com.avatye.cashblock.unit.cashbox.listener.ICashBoxReward
import com.avatye.cashblock.unit.cashbox.model.CashBoxInfo
import com.sudal.cashblock_box.databinding.ActivityCashBlockCashboxBinding

class CashBoxActivity : AppCompatActivity() {

    private val binding: ActivityCashBlockCashboxBinding by lazy {
        ActivityCashBlockCashboxBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // setProfile
        // TODO setUserProfile 이 접근이 안됨
//        CashBoxSDK.setUserProfile(
//            this@CashBoxActivity,
//        )


        // setRewardListener
        CashBoxSDK.setRewardListener(
            context = this@CashBoxActivity,
            listener = object : ICashBoxReward {
                override fun onReward(boxId: String, boxName: String) {
                    Log.e("asd", "CashBoxActivity -> onCreate -> onReward { boxId: $boxId, boxName:$boxName }")
                }

            }
        )
        //getBoxList()
        binding.btCashbox.setOnClickListener{
            CashBoxSDK.getBoxList(
                context = this@CashBoxActivity,
                listener = object : ICashBoxList {
                    override fun onResponse(cashBoxes: List<CashBoxInfo>) {
                        Log.e("asd", "CashBoxActivity -> onCreate -> { cashBoxes: $cashBoxes }")
                        launchCashBox()


                    }
                }
            )
        }
    }


    fun launchCashBox() {
        CashBoxSDK.launch(
            activity = this@CashBoxActivity,
            boxId = "",
            listener = object : ICashBoxLauncher {
                override fun onSuccess(launcher: CashBoxLauncher) {
                    Log.e("asd", "CashBoxActivity -> onClick::launchCashBox -> onSuccess")
                    launcher.open()
                }

                override fun onFailure(reason: String) {
                    Log.e("asd", "CashBoxActivity -> onClick::launchCashBox -> onFailure { reason: $reason }")
                }
            }
        )
    }
}