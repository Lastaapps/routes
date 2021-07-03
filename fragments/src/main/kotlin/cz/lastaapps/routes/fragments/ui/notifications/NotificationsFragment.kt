package cz.lastaapps.routes.fragments.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import cz.lastaapps.routes.fragments.Dests
import cz.lastaapps.routes.fragments.Message
import cz.lastaapps.routes.fragments.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        textView.text = requireArguments().getParcelable<Message>(Dests.Args.message)!!.message
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}